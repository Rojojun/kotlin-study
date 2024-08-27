package com.rojojun.kopring.repository

import com.rojojun.kopring.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {
}