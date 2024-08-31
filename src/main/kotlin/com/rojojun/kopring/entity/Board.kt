package com.rojojun.kopring.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "board")
data class Board(
    val title: String = "",
    val content: String = "",
    val likeUsers: List<User> = emptyList(),
) : BaseEntity() {
    fun update(title: String, content: String): Board = this.copy(title = title, content = content);
}
