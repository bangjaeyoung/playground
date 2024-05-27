package com.example.board.global.response

data class ApiResponse<T>(
    val header: ApiHeader,
    val data: T,
    val msg: String
) {
    companion object {
        private const val SUCCESS = 200
        
        fun <T> success(data: T, message: String): ApiResponse<T> {
            return ApiResponse(
                ApiHeader(SUCCESS, "SUCCESS"),
                data,
                message
            )
        }
        
        fun <T> fail(responseCode: ResponseCode, data: T): ApiResponse<T> {
            return ApiResponse(
                ApiHeader(responseCode.getHttpStatusCode(), responseCode.message),
                data,
                responseCode.message
            )
        }
    }
}
