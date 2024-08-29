package com.rojojun.kopring.dto

import com.rojojun.kopring.entity.Board

data class BoardRequestDto(
    var title: String,
    var content: String
) {
    fun toModel(): Board = Board(title, content);
}
