package com.example.board.api.v1.board.service.impl

import com.example.board.api.v1.board.controller.dto.RequestDto
import com.example.board.api.v1.board.domain.Board
import com.example.board.api.v1.board.service.BoardService
import com.example.board.mapper.BoardMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service("v1BoardService")
@Transactional(readOnly = true)
class BoardServiceImpl @Autowired constructor(
    val v1BoardMapper: BoardMapper
) : BoardService {

    @Transactional
    override fun postBoard(request: RequestDto): Long {
        val board = Board(
            title = request.title,
            content = request.content,
            writer = request.writer
        )

        v1BoardMapper.insertBoard(board)

        return board.boardId!!
    }
}
