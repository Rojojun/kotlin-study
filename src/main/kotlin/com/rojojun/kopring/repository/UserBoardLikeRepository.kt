package com.rojojun.kopring.repository

import com.rojojun.kopring.entity.UserBoardLike
import org.springframework.data.jpa.repository.JpaRepository

interface UserBoardLikeRepository : JpaRepository<UserBoardLike, Long> {
    fun findByUserIdAndBoardId(userId: Long, boardId: Long): UserBoardLike?;
}