package com.rojojun.kopring.repository

import com.rojojun.kopring.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsUserByEmail(email: String): Boolean;
    fun existsUserByNickname(nickname: String): Boolean;
    fun findByEmail(email: String): User?;
    fun findByNickname(nickname: String): User?;
}