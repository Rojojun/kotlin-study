package com.rojojun.kopring.service

import com.rojojun.kopring.dto.LoginDto
import com.rojojun.kopring.dto.SignUpDto
import com.rojojun.kopring.entity.User
import com.rojojun.kopring.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signUp(request: SignUpDto): User {
        val encryptedPassword: String = request.isSamePasswordAs(request.password)
            .run { passwordEncoder.encode(request.password) };

        val user = request.toDomain();
        user.changePassword(encryptedPassword);

        return userRepository.save(user);
    }

    fun login(request: LoginDto): Long {
        val encoredPassword = passwordEncoder.encode(request.password)
        val user = when {
            request.email != null -> userRepository.findByEmail(request.email)
            request.nickname != null -> userRepository.findByNickname(request.nickname)
            else -> throw IllegalArgumentException("email or nickname is null")
        }

        requireNotNull(user) {
            "User must not be null"
        }

        require(passwordEncoder.matches(encoredPassword, user.password)) {
            "Password is unmatched for email ${request.email}"
        }
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