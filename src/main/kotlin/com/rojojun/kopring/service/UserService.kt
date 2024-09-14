package com.rojojun.kopring.service

import com.rojojun.kopring.dto.LoginDto
import com.rojojun.kopring.dto.SignUpDto
import com.rojojun.kopring.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signUp(request: SignUpDto): Long {
        val encryptedPassword: String = request.isSamePasswordAs(request.password)
            .run { passwordEncoder.encode(request.password) };

        var user = request.toDomain();                  // 불변 객체 val을 사용하게 될 경우 비밀번호 변경이 안됨
        user = user.changePassword(encryptedPassword);

        return userRepository.save(user).id;
    }

    fun login(request: LoginDto): Long {
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

        val authentication: Authentication = UsernamePasswordAuthenticationToken(user, null)
        SecurityContextHolder.getContext().authentication = authentication

        return user.id;
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