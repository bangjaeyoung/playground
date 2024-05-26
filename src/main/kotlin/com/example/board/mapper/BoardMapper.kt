package com.example.board.mapper

import com.example.board.api.v1.board.domain.Board
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BoardMapper {
    fun insertBoard(board: Board)
}
