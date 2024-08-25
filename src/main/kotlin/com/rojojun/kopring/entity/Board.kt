package com.rojojun.kopring.entity

import jakarta.persistence.Entity

@Entity
data class Board(
    val title: String,
) : BaseEntity() {
    Board ()
}
