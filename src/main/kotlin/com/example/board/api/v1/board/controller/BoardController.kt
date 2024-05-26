package com.example.board.api.v1.board.controller

import com.example.board.api.v1.board.controller.dto.RequestDto
import com.example.board.api.v1.board.service.BoardService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api/v1/boards")
class BoardController @Autowired constructor(
    val v1BoardService: BoardService
) {
    @PostMapping
    fun postBoard(@Valid @RequestBody request: RequestDto): ResponseEntity<Long> {
        return ResponseEntity(v1BoardService.postBoard(request), HttpStatus.CREATED)
    }
}
