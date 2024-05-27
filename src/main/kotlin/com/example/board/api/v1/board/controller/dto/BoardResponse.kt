package com.example.board.api.v1.board.controller.dto

data class BoardResponse(
    val boardId: Long? = null,
    val title: String? = null,
    val content: String? = null,
    val writer: String? = null,
    val createdDate: String? = null,
    val modifiedDate: String? = null,
)
