package com.example.board.api.v1.board.controller.dto

import jakarta.validation.constraints.Size

data class RequestDto(
    @field:Size(max = 10, message = "제목은 최대 10자입니다.") val title: String,
    @field:Size(max = 200, message = "내용은 최대 200자입니다.") val content: String,
    val writer: String
)
