package com.example.board.api.v1.board.domain

data class Board(
    val boardId: Long? = null,
    val title: String? = null,
    val content: String? = null,
    val writer: String? = null,
    val createdDate: String? = null,
    val modifiedDate: String? = null,
    val deletedCheck: String? = null,
    val deletedDate: String? = null
)
