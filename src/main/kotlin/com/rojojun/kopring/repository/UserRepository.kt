package com.rojojun.kopring.repository

import com.rojojun.kopring.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
}