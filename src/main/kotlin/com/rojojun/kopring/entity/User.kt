package com.rojojun.kopring.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Column(unique = true, nullable = false)
    val nickname: String = "",
    val password: String = "",
    @Column(unique = true, nullable = false)
    val email: String = ""
): BaseEntity() {
    companion object {
        fun of(nickname: String, password: String, email: String): User = User(nickname, password, email);
    }
}
