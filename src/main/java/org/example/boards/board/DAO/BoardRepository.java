package org.example.boards.board.DAO;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.boards.board.Entity.Board;
import org.example.boards.board.Entity.Category;
import org.example.boards.board.Entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardRepository {

    List<Board> getBoards(@Param("headDate") String headDate,
                             @Param("tailDate") String tailDate,
                             @Param("searchWord") String searchKey);

    List<Category> getCategories();
    List<Comment> showComments(@Param("BOARD_ID") int BOARD_ID);
    Board showBoard(@Param("BOARD_ID") int BOARD_ID);
    int insertComment(Comment comment);
    int insertBoard(Board board);
    String checkPassword(@Param("password") String password, @Param("key")String key);
    int deleteBoard(@Param("boardId") int boardId);
   // int increaseViews(@Param("boardId") int boardId);
}
