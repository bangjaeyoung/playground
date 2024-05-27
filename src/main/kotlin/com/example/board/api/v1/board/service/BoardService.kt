package com.example.board.api.v1.board.service

import com.example.board.api.v1.board.controller.dto.BoardRequest
import com.example.board.api.v1.board.controller.dto.BoardResponse
import com.example.board.global.response.Page

interface BoardService {
    // 게시글 생성
    fun postBoard(request: BoardRequest.Post): Long
    
    // 게시글 수정
    fun updateBoard(boardId: Long, request: BoardRequest.Update)
    
    // 게시글 단일 조회
    fun getBoardById(boardId: Long): BoardResponse
    
    // 게시글 전체 조회
    fun getAllBoards(page: Int, size: Int): Page<BoardResponse>
    
    // 게시글 단일 삭제
    fun deleteBoardById(boardId: Long)
    
    // 게시글 전체 삭제
    fun deleteAllBoards()
}
