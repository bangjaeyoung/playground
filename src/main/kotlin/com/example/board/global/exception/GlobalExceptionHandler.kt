package com.example.board.global.exception

import com.example.board.global.response.ApiResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    
    @ExceptionHandler(BoardException::class)
    fun handleUserException(e: BoardException): ApiResponse<Void?> {
        return ApiResponse.fail(e.responseCode, null)
    }
}
