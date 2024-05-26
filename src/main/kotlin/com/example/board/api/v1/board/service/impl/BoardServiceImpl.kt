package com.example.board.api.v1.board.service.impl

import com.example.board.api.v1.board.controller.dto.RequestDto
import com.example.board.api.v1.board.controller.dto.ResponseDto
import com.example.board.api.v1.board.domain.Board
import com.example.board.api.v1.board.service.BoardService
import com.example.board.mapper.BoardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("v1BoardService")
@Transactional(readOnly = true)
class BoardServiceImpl @Autowired constructor(
    val v1BoardMapper: BoardMapper
) : BoardService {
    
    @Transactional
    override fun postBoard(request: RequestDto.Post): Long {
        val board = Board(
            title = request.title,
            content = request.content,
            writer = request.writer
        )
        v1BoardMapper.insertBoard(board)
        
        return board.boardId!!
    }
    
    @Transactional
    override fun updateBoard(boardId: Long, request: RequestDto.Update) {
        val board = Board(
            boardId = boardId,
            title = request.title,
            content = request.content
        )
        v1BoardMapper.updateBoard(board)
    }
    
    override fun getBoardById(boardId: Long): ResponseDto? {
        val board = v1BoardMapper.selectBoardById(boardId) ?: return null
        return ResponseDto(
            boardId = board.boardId,
            title = board.title,
            content = board.content,
            writer = board.writer,
            createdDate = board.createdDate.toString(),
            modifiedDate = board.modifiedDate.toString()
        )
    }
}
