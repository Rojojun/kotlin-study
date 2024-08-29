package com.rojojun.kopring.dto

import com.rojojun.kopring.entity.Board

data class BoardResponseDto(
    val title: String,
    val content: String
) {
    companion object {
        fun from(board: Board): BoardResponseDto = BoardResponseDto(board.title, board.content);
    }
}