package com.rojojun.kopring.dto

import com.rojojun.kopring.entity.User

data class SignUpDto(
    val email: String,
    val nickname: String,
    val password: String,
    val checkPassword: String
) {
    fun toDomain(): User = User(email, nickname, password)
    fun isSamePasswordAs(password: String) = require(password == checkPassword) { ("비밀번호 불일치") }
}