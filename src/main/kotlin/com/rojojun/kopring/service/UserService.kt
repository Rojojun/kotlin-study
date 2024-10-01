package com.rojojun.kopring.service

import com.rojojun.kopring.dto.LoginDto
import com.rojojun.kopring.dto.LoginResponseDto
import com.rojojun.kopring.dto.SignUpDto
import com.rojojun.kopring.repository.UserRepository
import com.rojojun.kopring.security.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val  jwtUtil: JwtUtil
) {
    fun signUp(request: SignUpDto): Long {
        val encryptedPassword: String = request.isSamePasswordAs(request.password)
            .run { passwordEncoder.encode(request.password) };

        var user = request.toDomain();                  // 불변 객체 val을 사용하게 될 경우 비밀번호 변경이 안됨
        user = user.changePassword(encryptedPassword);

        return userRepository.save(user).id;
    }

    fun login(request: LoginDto): LoginResponseDto {
        val user = when {
            request.email != null -> userRepository.findByEmail(request.email)
            request.nickname != null -> userRepository.findByNickname(request.nickname)
            else -> throw IllegalArgumentException("email or nickname is null")
        }

        requireNotNull(user) {
            "User must not be null"
        }

        require(passwordEncoder.matches(request.password, user.password)) {
            "Password is unmatched for email ${request.email ?: request.nickname}"
        }
        val principal = user.email ?: user.nickname ?: throw IllegalArgumentException("User must have an email or nickname")
        val authorities: List<SimpleGrantedAuthority> = emptyList()
        val authentication: Authentication = UsernamePasswordAuthenticationToken(principal, null, authorities)
        SecurityContextHolder.getContext().authentication = authentication
        val token: String = jwtUtil.generateToken(authentication.name)
//        val authentication: Authentication = UsernamePasswordAuthenticationToken(user, null)
//        SecurityContextHolder.getContext().authentication = authentication
//        val token: String = jwtUtil.generateToken(authentication.name)

        return LoginResponseDto(user.id, token);
    }

    fun validateNickname(nickname: String) =
        require(!userRepository.existsUserByNickname(nickname)) {
            "User already exists"
    }

    fun validateEmail(email: String) =
        require(!userRepository.existsUserByEmail(email)) {
            "User doesn't have email"
    }
}