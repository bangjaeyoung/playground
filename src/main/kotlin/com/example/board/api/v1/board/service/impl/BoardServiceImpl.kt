package com.example.board.api.v1.board.service.impl

import com.example.board.api.v1.board.controller.dto.BoardRequest
import com.example.board.api.v1.board.controller.dto.BoardResponse
import com.example.board.api.v1.board.domain.Board
import com.example.board.api.v1.board.service.BoardService
import com.example.board.global.exception.BoardException
import com.example.board.global.mapper.BoardMapper
import com.example.board.global.response.Page
import com.example.board.global.response.ResponseCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("v1BoardService")
@Transactional(readOnly = true)
class BoardServiceImpl @Autowired constructor(
    val v1BoardMapper: BoardMapper
) : BoardService {
    
    @Transactional
    override fun postBoard(request: BoardRequest.Post): Long {
        val board = Board(
            title = request.title,
            content = request.content,
            writer = request.writer
        )
        v1BoardMapper.insertBoard(board)
        
        return board.boardId!!
    }
    
    @Transactional
    override fun updateBoard(boardId: Long, request: BoardRequest.Update) {
        verifyNonExistedBoard(boardId)
        
        val board = Board(
            boardId = boardId,
            title = request.title,
            content = request.content
        )
        v1BoardMapper.updateBoard(board)
    }
    
    override fun getBoardById(boardId: Long): BoardResponse {
        val board = verifyNonExistedBoard(boardId)
        return BoardResponse(
            boardId = board.boardId,
            title = board.title,
            content = board.content,
            writer = board.writer,
            createdDate = board.createdDate.toString(),
            modifiedDate = board.modifiedDate.toString()
        )
    }
    
    override fun getAllBoards(
        page: Int,
        size: Int,
        searchType: String?,
        searchText: String?,
        orderCriteria: String,
        order: String
    ): Page<BoardResponse> {
        val totalElements = v1BoardMapper.countAllBoards(searchType, searchText)
        val totalPages = (totalElements + size - 1) / size
        val offset = (page - 1) * size
        val allBoards = v1BoardMapper.selectAllBoards(size, offset, searchType, searchText, orderCriteria, order)
        
        val data = allBoards.map { board ->
            BoardResponse(
                boardId = board.boardId,
                title = board.title,
                content = board.content,
                writer = board.writer,
                createdDate = board.createdDate,
                modifiedDate = board.modifiedDate
            )
        }
        
        return Page(data, totalElements, totalPages, page)
    }
    
    @Transactional
    override fun deleteBoardById(boardId: Long) {
        verifyNonExistedBoard(boardId)
        v1BoardMapper.deleteBoardById(boardId)
    }
    
    @Transactional
    override fun deleteAllBoards() {
        v1BoardMapper.deleteAllBoards()
    }
    
    private fun verifyNonExistedBoard(boardId: Long): Board {
        return v1BoardMapper.selectBoardById(boardId) ?: throw BoardException(ResponseCode.POST_NOT_FOUND)
    }
}
