package org.example.boards.board.Mapper;

import org.example.boards.board.DTO.BoardDTO;
import org.example.boards.board.DTO.CategoryDTO;
import org.example.boards.board.Entity.Board;
import org.example.boards.board.Entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board toboard(BoardDTO boardDTO);
    BoardDTO toboardDto(Board board);

    List<Board> toBoardList(List<BoardDTO> dtoList);
    List<BoardDTO> toBoardDtoList(List<Board> entityList);

    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> toCategoryDtoList(List<Category> entityList);
    List<Category> toCategoryList(List<CategoryDTO> dtoList);

}
