package com.example.board.global.exception

import com.example.board.global.response.ApiResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalExceptionHandler {
    
    @ExceptionHandler(BoardException::class)
    fun handleUserException(e: BoardException): ApiResponse<Void?> {
        logger.info { "${"BoardException: {}"} ${e.message}" }
        return ApiResponse.fail(e.responseCode, null)
    }
}
