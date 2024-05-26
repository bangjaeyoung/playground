package com.example.board.api.v1.board.controller.dto

import jakarta.validation.constraints.Size

class RequestDto {
    data class Post(
        @field:Size(min = 1, max = 10, message = "제목 글자수 제한은 1 ~ 10자입니다.") val title: String,
        @field:Size(min = 1, max = 200, message = "내용 글자수 제한은 1 ~ 200자입니다.") val content: String,
        val writer: String
    )
    
    data class Update(
        @field:Size(min = 1, max = 10, message = "제목 글자수 제한은 1 ~ 10자입니다.") val title: String?,
        @field:Size(min = 1, max = 200, message = "내용 글자수 제한은 1 ~ 200자입니다.") val content: String?,
    )
}
