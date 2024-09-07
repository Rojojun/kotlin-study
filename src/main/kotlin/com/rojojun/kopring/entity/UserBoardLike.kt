package com.rojojun.kopring.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class UserBoardLike(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    val user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    val board: Board
): BaseEntity() {
    constructor() : this(User(), Board())

    companion object {
        fun of(user: User, board: Board) = UserBoardLike(user = user, board = board)
    }
}
