package com.rojojun.kopring.entity

import jakarta.persistence.*

@Entity
@Table(name = "reply")
data class Reply(
    val content: String = "",
    val status: Status = Status.USED,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    val board: Board
): BaseEntity() {
    constructor() : this("", Status.USED, Board())

    companion object {
        fun create(content: String, board: Board): Reply {
            return Reply(content = content, board = board)
        }
    }

    fun update(content: String) {
        this.copy(content = content)
    }

    fun delete(status: Status) {
        this.copy(status = Status.DELETED);
    }
}
