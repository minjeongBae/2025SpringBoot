package org.example.boards.board.Mapper;

import org.example.boards.board.DTO.BoardListDTO;
import org.example.boards.board.DTO.BoardViewDTO;
import org.example.boards.board.DTO.CategoryDTO;
import org.example.boards.board.DTO.CommentDTO;
import org.example.boards.board.Entity.Board;
import org.example.boards.board.Entity.Category;
import org.example.boards.board.Entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board toBoardForList(BoardListDTO boardListDTO);
    BoardListDTO toBoardDto(Board board);

    List<Board> toBoardList(List<BoardListDTO> dtoList);
    List<BoardListDTO> toBoardDtoList(List<Board> entityList);

    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> toCategoryDtoList(List<Category> entityList);
    List<Category> toCategoryList(List<CategoryDTO> dtoList);


    BoardViewDTO toBoardViewDTO(Board board);
    Board toBoardForView(Board board);

    CommentDTO toCommentDTO(Comment comment);
    Comment toComment(CommentDTO commentDTO);

    List<CommentDTO> toCommentDtoList(List<Comment> entityList);
    List<Comment> toCommentList(List<CommentDTO> dtoList);
}
