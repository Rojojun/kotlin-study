package com.rojojun.kopring.service

import com.rojojun.kopring.dto.BoardListResponseDto
import com.rojojun.kopring.dto.BoardRequestDto
import com.rojojun.kopring.dto.BoardResponseDto
import com.rojojun.kopring.entity.Board
import com.rojojun.kopring.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(private val boardRepository: BoardRepository) {
    fun saveBoard(request: BoardRequestDto): () -> BoardResponseDto = {
        val board = boardRepository.save(request.toModel())
        BoardResponseDto(board)
    }

    fun findAll(): () -> List<BoardListResponseDto> = {
        val boardList: List<Board> = boardRepository.findAll();
        boardList.map { BoardListResponseDto.from(it) }
    }

    fun getBoard(id: Long): () -> BoardResponseDto = {
        val board = boardRepository.findById(id)
            .orElseThrow { throw RuntimeException("Board with id $id not found") }
        BoardResponseDto(board)
    }

    fun editBoard(requestDto: BoardRequestDto, id: Long): () -> BoardResponseDto = {
        val board = boardRepository.findById(id)
            .orElseThrow { throw RuntimeException("Board with id $id not found") }

        board.update(requestDto.title, requestDto.content);
        BoardResponseDto(board)
    }
}