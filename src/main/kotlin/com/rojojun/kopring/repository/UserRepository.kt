package com.rojojun.kopring.repository

import com.rojojun.kopring.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun existsUserByEmail(email: String): Boolean;
    fun existsUserByNickname(nickname: String): Boolean;
}