package com.rojojun.kopring.dto

import com.rojojun.kopring.entity.Board
import java.time.LocalDateTime

data class BoardResponseDto(
    val id: Long,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val isModified: Boolean,
    val title: String,
    val content: String
) {
    constructor(board: Board): this(
        id = board.id,
        createdAt = board.createdDate,
        modifiedAt = board.modifiedDate,
        isModified = board.createdDate == board.modifiedDate,
        title = board.title,
        content = board.content
    )
}