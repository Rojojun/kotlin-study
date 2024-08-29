package com.rojojun.kopring.dto

import com.rojojun.kopring.entity.Board

data class BoardListResponseDto(
    val title : String,
    val content : String
) {
    companion object {
        fun from(board: Board): BoardListResponseDto = BoardListResponseDto(board.title, board.content);
    }
}