package com.rojojun.kopring.controller

import com.rojojun.kopring.dto.BoardRequestDto
import com.rojojun.kopring.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class BoardController(private val boardService: BoardService) {

    @GetMapping("/board/list")
    fun getBoardList() = ResponseEntity.ok().body(boardService.findAll());

    @GetMapping("/board/{id}")
    fun getBoard(@RequestParam(name = "id", required = true) id: Long) = ResponseEntity.ok().body(boardService.getBoard(id));

    @PostMapping("/board/add")
    fun addBoard(@RequestBody requestDto: BoardRequestDto, principal: Principal) = ResponseEntity.ok().body(boardService.saveBoard(requestDto, principal.name))

    @PostMapping("/board/edit/{id}")
    fun editBoard(@RequestBody requestDto: BoardRequestDto,
                  @PathVariable(name = "id", required = true) id: Long) =
        ResponseEntity.ok().body(boardService.editBoard(requestDto, id))
}