package com.rojojun.kopring.service

import com.rojojun.kopring.dto.SignUpDto
import com.rojojun.kopring.repository.UserRepository
import org.apache.catalina.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun signUp(request: SignUpDto): () -> User = {
        request.isSamePasswordAs(request.password);
        userRepository.save()
    }
}