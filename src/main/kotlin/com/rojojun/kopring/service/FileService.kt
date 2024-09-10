package com.rojojun.kopring.service

import com.rojojun.kopring.dto.ResourceDto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileService {

    fun fileUpload(files: List<MultipartFile>): () -> Unit = {
        if (files.isEmpty()) {
            throw IllegalArgumentException("File list is empty")
        }

        files.stream().map { file ->  }
    }
}