package com.rojojun.kopring.service

import com.rojojun.kopring.dto.BoardListResponseDto
import com.rojojun.kopring.dto.BoardRequestDto
import com.rojojun.kopring.dto.BoardResponseDto
import com.rojojun.kopring.entity.Board
import com.rojojun.kopring.entity.UserBoardLike
import com.rojojun.kopring.repository.BoardRepository
import com.rojojun.kopring.repository.UserBoardLikeRepository
import com.rojojun.kopring.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
    private val userBoardLikeRepository: UserBoardLikeRepository
) {
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

    fun increaseOrDecreaseBoardLikeCount(boardId: Long, userId: Long): Long {
        val board = boardRepository.findById(boardId)
            .orElseThrow { throw RuntimeException("Board with id $boardId not found") }
        val user = userRepository.findById(userId)
            .orElseThrow { throw RuntimeException("User with id $userId not found") }

        val userBoardLike: UserBoardLike = userBoardLikeRepository.findByUserIdAndBoardId(boardId, userId)
            ?: return userBoardLikeRepository.save(UserBoardLike.of(user, board)).id;

        userBoardLikeRepository.delete(userBoardLike)
        return userBoardLike.id
    }
}