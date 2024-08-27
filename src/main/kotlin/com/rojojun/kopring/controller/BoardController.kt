package com.rojojun.kopring.controller

import com.rojojun.kopring.entity.Board
import com.rojojun.kopring.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(private val boardService: BoardService) {

    @GetMapping("/board/list")
    fun getBoardList() = ResponseEntity.ok().body(boardService.findAll());
}