package com.example.board.api.v1.board.controller

import com.example.board.api.v1.board.controller.dto.BoardRequest
import com.example.board.api.v1.board.controller.dto.BoardResponse
import com.example.board.api.v1.board.service.BoardService
import com.example.board.global.response.ApiResponse
import com.example.board.global.response.ResponseCode
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api/v1/boards")
class BoardController @Autowired constructor(
    val v1BoardService: BoardService,
) {
    
    @PostMapping
    fun postBoard(@Valid @RequestBody request: BoardRequest.Post): ApiResponse<Long> {
        return ApiResponse.success(
            v1BoardService.postBoard(request),
            ResponseCode.POST_CREATE_SUCCESS.message
        );
    }
    
    @PatchMapping("/{board-id}")
    fun updateBoard(
        @Positive @PathVariable("board-id") boardId: Long,
        @Valid @RequestBody request: BoardRequest.Update
    ): ApiResponse<HttpStatus> {
        v1BoardService.updateBoard(boardId, request)
        return ApiResponse.success(
            ResponseCode.POST_UPDATE_SUCCESS.httpStatus,
            ResponseCode.POST_UPDATE_SUCCESS.message
        )
    }
    
    @GetMapping("/{board-id}")
    fun getBoard(@Positive @PathVariable("board-id") boardId: Long): ApiResponse<BoardResponse> {
        return ApiResponse.success(
            v1BoardService.getBoardById(boardId),
            ResponseCode.POST_READ_SUCCESS.message
        )
    }
    
    @GetMapping
    fun getBoards(): ApiResponse<List<BoardResponse>> {
        return ApiResponse.success(
            v1BoardService.getAllBoards(),
            ResponseCode.POST_READ_SUCCESS.message
        )
    }
    
    @DeleteMapping("/{board-id}")
    fun deleteBoard(@Positive @PathVariable("board-id") boardId: Long): ApiResponse<HttpStatus> {
        v1BoardService.deleteBoardById(boardId)
        return ApiResponse.success(
            ResponseCode.POST_DELETE_SUCCESS.httpStatus,
            ResponseCode.POST_DELETE_SUCCESS.message,
        )
    }
    
    @DeleteMapping
    fun deleteAllBoards(): ApiResponse<HttpStatus> {
        v1BoardService.deleteAllBoards()
        return ApiResponse.success(
            ResponseCode.POST_DELETE_SUCCESS.httpStatus,
            ResponseCode.POST_DELETE_SUCCESS.message,
        )
    }
}
