package com.rojojun.kopring.dto

import com.rojojun.kopring.entity.BoardResource

data class ResourceDto(
    val fileName: String,
    val filePath: String,
    val fileSize: Long
) {
}