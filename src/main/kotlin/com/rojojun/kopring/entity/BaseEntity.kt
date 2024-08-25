package com.rojojun.kopring.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L;

    @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now();

    @LastModifiedDate
    val modifiedDate: LocalDateTime = LocalDateTime.now();

    @CreatedBy
    val createdBy: String = "";

    @LastModifiedBy
    val modifiedBy: String = "";
}