package com.rojojun.kopring.controller

import com.rojojun.kopring.dto.BoardRequestDto
import com.rojojun.kopring.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(private val boardService: BoardService) {

    @GetMapping("/board/list")
    fun getBoardList() = ResponseEntity.ok().body(boardService.findAll());

    @GetMapping("/board/{id}")
    fun getBoard(@RequestParam id: Long) = ResponseEntity.ok().body(boardService.getBoard(id));

    @PostMapping("/board/add")
    fun addBoard(@RequestBody requestDto: BoardRequestDto): () -> ResponseEntity.BodyBuilder = {
        boardService.saveBoard(requestDto);
        ResponseEntity.ok();
    }
}