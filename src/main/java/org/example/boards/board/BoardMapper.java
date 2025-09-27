package org.example.boards.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.boards.board.DTO.BoardDTO;
import org.example.boards.board.DTO.CategoryDTO;
import org.example.boards.board.DTO.CommentDTO;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> findAllBoards();
    List<BoardDTO> searchBoards(@Param("headDate") LocalDate headDate,
                                @Param("tailDate") LocalDate tailDate,
                                @Param("searchWord") String searchWord);

    List<CategoryDTO> findAllCategories();
    List<CommentDTO> showComments(@Param("BOARD_ID") int BOARD_ID);
    BoardDTO showBoard(@Param("BOARD_ID") int BOARD_ID);
    int insertComment(CommentDTO commentDTO);
}
