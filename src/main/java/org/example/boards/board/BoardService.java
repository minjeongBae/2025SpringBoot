package org.example.boards.board;

import lombok.RequiredArgsConstructor;
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

    final static String key = "PASSWORD";
    /**
     *
     * @param searchKey
     * @return 검색조건으로 조회
     */
    public List<BoardListDTO> getBoards(SearchKeyDTO searchKey) {

        Util validation  = new Util();

        List<Board> boards = boardRepository.getBoards(validation.getHeadDateStr(searchKey.getHeadDate())
                 , validation.getTailDateStr(searchKey.getTailDate())
                 , validation.getSearchKeyStr(searchKey.getWord())
                 , searchKey.getCategory());

        return boardMapper.toBoardDtoList(boards);
    }

    public List<CategoryDTO> getCategories(){
        List<Category> categories = boardRepository.getCategories();

        return boardMapper.toCategoryDtoList(categories);
    }

    public BoardViewDTO getBoardView(int boardId){
        if(!isRealBoard(boardId)) return null;
        Board board = boardRepository.getBoard(boardId);
        return boardMapper.toBoardViewDTO(board);
    }

    private boolean isRealBoard(int boardId){
        if (boardRepository.isRealBoard(boardId) == 1) {
            return true;
        }
        //throw new NullPointerException("존재하지 않는 게시물입니다.");
        return false;
    }

    public List<CommentDTO> getComments(int boardId){
        if(!isRealBoard(boardId)) return null;
        List<Comment> comments = boardRepository.getComments(boardId);

        return boardMapper.toCommentDtoList(comments);
    }

    public int insertBoard(NewBoardDTO newBoardDTO) {
        Board board = boardMapper.newBoardToEntity(newBoardDTO);
        System.out.println("getContent:"+board.getContent());
        int res = boardRepository.insertBoard(board, key);
        if(res==1){
            return boardRepository.getMaxBoardId();
        }
        return -1;
    }

    public boolean insertComment(CommentDTO commentDTO) {
        if(!isRealBoard(commentDTO.getBoardId())) return false;
        Comment comment = boardMapper.toComment(commentDTO);
        int res = boardRepository.insertComment(comment);

        return res==1;
    }

    public boolean deleteBoard(int boardId, String password){
        if(!isRealBoard(boardId)) return false;

        if(!checkPw(boardId, password)) return false;

        return boardRepository.deleteBoard(boardId) == 1;
    }

    private boolean checkPw(int boardId, String password){
        // TODO
        if(boardRepository.checkPassword(boardId, password, key) == 1) {
            return true;
        }
        return false;
    }
}
