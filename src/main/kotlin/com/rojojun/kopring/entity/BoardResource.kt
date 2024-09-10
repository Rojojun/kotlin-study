package com.rojojun.kopring.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class BoardResource(
    val status: Status = Status.USED,
    val fileName: String = "",
    val fileSize: Long = 0L,
    val filePath: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    val board: Board
): BaseEntity() {
    constructor() : this(Status.USED, "", 0L, "", Board())

    fun delete(): BoardResource = this.copy(status = Status.DELETED)
}
