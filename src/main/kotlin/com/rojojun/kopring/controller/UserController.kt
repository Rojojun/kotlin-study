package com.rojojun.kopring.controller

import com.rojojun.kopring.dto.LoginDto
import com.rojojun.kopring.dto.SignUpDto
import com.rojojun.kopring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @PostMapping("/signup")
    fun singUp(@RequestBody signUpDto: SignUpDto): ResponseEntity<Long> = ResponseEntity.ok(userService.signUp(signUpDto))

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<Long> = ResponseEntity.ok(userService.login(loginDto))
}