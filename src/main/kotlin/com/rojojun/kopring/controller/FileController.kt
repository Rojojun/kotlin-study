package com.rojojun.kopring.controller

import com.rojojun.kopring.service.FileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController(private val fileService: FileService) {
    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") files: List<MultipartFile>) = ResponseEntity.ok(fileService.fileUpload(files))
}