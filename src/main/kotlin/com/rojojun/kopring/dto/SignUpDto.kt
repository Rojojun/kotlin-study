package com.rojojun.kopring.dto

data class SignUpDto(
    val email: String,
    val nickname: String,
    val password: String,
    val checkPassword: String
) {
    fun isSamePasswordAs(password: String) = require(password == checkPassword) { ("비밀번호 불일치") }
}