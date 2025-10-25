package org.example.boards.board;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.example.boards.board.DAO.BoardRepository;
import org.example.boards.board.DTO.*;
import org.example.boards.board.Entity.Board;
import org.example.boards.board.Entity.Category;
import org.example.boards.board.Entity.Comment;
import org.example.boards.board.Mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
public class BoardService {

    final BoardRepository boardRepository;
    final BoardMapper boardMapper;

    public List<BoardListDTO> getBoards(SearchKeyDTO searchKey) {
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
        List<Board> boards = boardRepository.getBoards(headDate, tailDate, word);

        return boardMapper.toBoardDtoList(boards);
    }

    public List<CategoryDTO> getCategories(){
        List<Category> categories = boardRepository.getCategories();

        return boardMapper.toCategoryDtoList(categories);
    }

    public BoardViewDTO getBoardView(String boardId){
        if(!checkRealBoard(boardId)) return null;
        Board board = boardRepository.getBoard(Integer.parseInt(boardId));
        return boardMapper.toBoardViewDTO(board);
    }

    // TODO Exception이 나은가
    private boolean checkRealBoard(String boardId){
        if(boardId==null || !boardId.matches("\\d+")) {
            //throw new NullPointerException("존재하지 않는 게시물입니다.");
            return false;
        }
        if (boardRepository.isRealBoard(Integer.parseInt(boardId)) != 1) {
            //throw new NullPointerException("존재하지 않는 게시물입니다.");
            return false;
        }
        return true;
    }

    public List<CommentDTO> getComments(String boardId){
        if(!checkRealBoard(boardId)) return null;
        List<Comment> comments = boardRepository.getComments(Integer.parseInt(boardId));

        return boardMapper.toCommentDtoList(comments);
    }

    public int insertComment(CommentDTO commentDTO) {
        if(!checkRealBoard(String.valueOf(commentDTO.getBoardId()))) return 0;
        Comment comment = boardMapper.toComment(commentDTO);
        int res = boardRepository.insertComment(comment);
        System.out.println("res: "+res);
        return res;
    }
}
