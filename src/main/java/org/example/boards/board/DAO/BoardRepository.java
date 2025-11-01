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
                             @Param("searchWord") String searchKey,
                             @Param("category") int category);

    List<Category> getCategories();

    int isRealBoard(@Param("boardId") int boardId);

    Board getBoard(@Param("boardId") int boardId);

    List<Comment> getComments(@Param("boardId") int boardId);

    int insertComment(Comment comment);

    int getMaxBoardId();

    int checkPassword(@Param("boardId") int boardId, @Param("password") String password, @Param("key")String key);

    int insertBoard(@Param("board") Board board, @Param("key")String key);
    int deleteBoard(@Param("boardId") int boardId);
   // int increaseViews(@Param("boardId") int boardId);
}
