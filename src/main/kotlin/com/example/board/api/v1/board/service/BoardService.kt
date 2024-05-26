package com.example.board.api.v1.board.service

import com.example.board.api.v1.board.controller.dto.RequestDto

interface BoardService {
    // 게시글 생성
    fun postBoard(request: RequestDto): Long
}
