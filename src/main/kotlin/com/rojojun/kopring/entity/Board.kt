package com.rojojun.kopring.entity

import jakarta.persistence.*
import java.util.TreeSet

@Entity
@Table(name = "board")
data class Board(
    val title: String = "",
    val content: String = "",
    val status: Status = Status.USED,
    @OneToMany(mappedBy = "board")
    val likeUsers: MutableSet<User> = TreeSet(),
    @OneToMany(mappedBy = "board")
    val replyList: MutableSet<Reply> = TreeSet()
) : BaseEntity() {
    fun update(title: String, content: String): Board = this.copy(title = title, content = content);

    fun delete(): Board = this.copy(status = Status.DELETED)
}
