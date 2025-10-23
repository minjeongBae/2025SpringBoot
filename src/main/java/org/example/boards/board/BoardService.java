package org.example.boards.board;

import lombok.RequiredArgsConstructor;
import org.example.boards.board.DAO.BoardRepository;
import org.example.boards.board.DTO.BoardDTO;
import org.example.boards.board.DTO.CategoryDTO;
import org.example.boards.board.DTO.SearchKeyDTO;
import org.example.boards.board.Entity.Board;
import org.example.boards.board.Entity.Category;
import org.example.boards.board.Mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
public class BoardService {

    final BoardRepository boardRepository;
    final BoardMapper boardMapper;

    public List<BoardDTO> getBoards(SearchKeyDTO searchKey) {
        List<Board> boards;
        String headDate = "20000101";
        String tailDate = "29991231";
        String word = "";
        if(searchKey.getWord() != null && !searchKey.getWord().isEmpty()) {
            word = searchKey.getWord();
        }
        if(searchKey.getHeadDate() != null) {
            headDate = String.valueOf(searchKey.getHeadDate());
        }
        if(searchKey.getTailDate() != null) {
            tailDate = String.valueOf(searchKey.getTailDate());
        }
        boards = boardRepository.getBoards(headDate, tailDate, word);

        return boardMapper.toBoardDtoList(boards);
    }

    public List<CategoryDTO> getCategories(){
        List<Category> categories;
        categories = boardRepository.getCategories();

        return boardMapper.toCategoryDtoList(categories);
    }
}
