package com.example.board.api.v1.board.service

import com.example.board.api.v1.board.controller.dto.RequestDto
import com.example.board.api.v1.board.controller.dto.ResponseDto

interface BoardService {
    // 게시글 생성
    fun postBoard(request: RequestDto.Post): Long
    
    // 게시글 수정
    fun updateBoard(boardId: Long, request: RequestDto.Update)
    
    // 게시글 단일 조회
    fun getBoardById(boardId: Long): ResponseDto?
    
    // 게시글 전체 조회
    fun getAllBoards(): List<ResponseDto>
    
    // 게시글 단일 삭제
    fun deleteBoardById(boardId: Long)
    
    // 게시글 전체 삭제
    fun deleteAllBoards()
}
