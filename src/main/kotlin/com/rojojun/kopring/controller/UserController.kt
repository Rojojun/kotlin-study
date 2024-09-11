package com.rojojun.kopring.controller

import com.rojojun.kopring.dto.SignUpDto
import com.rojojun.kopring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    fun singUp(@RequestBody signUpDto: SignUpDto): ResponseEntity<Long> = ResponseEntity.ok(userService.signUp(signUpDto))
}