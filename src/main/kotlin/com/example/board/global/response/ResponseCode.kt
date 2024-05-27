package com.example.board.global.response

import org.springframework.http.HttpStatus

enum class ResponseCode(
    val httpStatus: HttpStatus,
    val success: Boolean,
    val message: String
) {
    // 400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),
    
    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, false, "권한이 없습니다."),
    
    // 404 Not Found
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, false, "해당 게시글을 찾을 수 없습니다."),
    
    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, false, "허용되지 않은 메소드입니다."),
    
    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버에 오류가 발생하였습니다."),
    
    // 200 OK
    POST_READ_SUCCESS(HttpStatus.OK, true, "게시글 조회를 성공하였습니다."),
    
    // 201 Created
    POST_CREATE_SUCCESS(HttpStatus.CREATED, true, "게시글 생성을 성공하였습니다."),
    
    // 204 No Content
    POST_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, true, "게시글 수정을 성공하였습니다."),
    POST_DELETE_SUCCESS(HttpStatus.NO_CONTENT, true, "게시글 삭제를 성공하였습니다.");
    
    fun getHttpStatusCode(): Int {
        return httpStatus.value()
    }
}
