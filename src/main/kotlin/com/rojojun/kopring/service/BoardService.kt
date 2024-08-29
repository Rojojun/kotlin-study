package com.rojojun.kopring.service

import com.rojojun.kopring.dto.BoardRequestDto
import com.rojojun.kopring.entity.Board
import com.rojojun.kopring.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(private val boardRepository: BoardRepository) {
    fun saveBoard(request: BoardRequestDto): Board = boardRepository.save(request.toModel())

    fun findAll(): List<Board> = boardRepository.findAll();
}