package com.rojojun.kopring.service

import com.rojojun.kopring.entity.Board
import com.rojojun.kopring.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(private val boardRepository: BoardRepository) {
//    fun findAll(): List<Board> {
//        return boardRepository.findAll();
//    }
    fun findAll(): List<Board> = boardRepository.findAll();
}