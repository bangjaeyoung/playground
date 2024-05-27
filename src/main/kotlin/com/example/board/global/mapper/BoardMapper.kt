package com.example.board.global.mapper

import com.example.board.api.v1.board.domain.Board
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface BoardMapper {
    fun insertBoard(board: Board)
    fun updateBoard(board: Board)
    fun selectBoardById(boardId: Long): Board?
    fun selectAllBoards(@Param("limit") limit: Int, @Param("offset") offset: Int): List<Board>
    fun countAllBoards(): Int
    fun deleteBoardById(boardId: Long)
    fun deleteAllBoards()
}
