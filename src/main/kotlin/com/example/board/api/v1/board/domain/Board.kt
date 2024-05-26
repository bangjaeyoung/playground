package com.example.board.api.v1.board.domain

import java.time.LocalDateTime

data class Board(
    val boardId: Long? = null,
    val title: String? = null,
    val content: String? = null,
    val writer: String? = null,
    val createdDate: LocalDateTime? = null,
    val modifiedDate: LocalDateTime? = null,
)
