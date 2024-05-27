package com.example.board.global.exception

import com.example.board.global.response.ResponseCode

open class BaseException(val responseCode: ResponseCode) : RuntimeException() {
    override val message: String? get() = responseCode.message
}

class BoardException(responseCode: ResponseCode) : BaseException(responseCode)
