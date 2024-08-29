package com.rojojun.kopring.service

import com.rojojun.kopring.dto.BoardRequestDto
import com.rojojun.kopring.entity.Board
import com.rojojun.kopring.repository.BoardRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class BoardServiceTest {
    @Autowired
    private lateinit var boardService: BoardService
    @MockBean
    private lateinit var boardRepository: BoardRepository

    @Test
    fun `저장 테스트 로직`() {
        // given
        val boardRequestDto = BoardRequestDto(
            title = "test_title",
            content = "test_content"
        );
        val board = Board(
            title = "test_title",
            content = "test_content"
        );
        whenever(boardRepository.save(boardRequestDto.toModel())).thenReturn(board)

        // when
        val savedBoard = boardService.saveBoard(boardRequestDto);

        // then
        assertEquals(boardRequestDto.title, savedBoard.title)
        assertEquals(boardRequestDto.content, savedBoard.content)
    }
}