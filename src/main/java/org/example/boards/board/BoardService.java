package org.example.boards.board;

import lombok.RequiredArgsConstructor;
import org.example.boards.board.DAO.BoardRepository;
import org.example.boards.board.DTO.*;
import org.example.boards.board.Entity.*;
import org.example.boards.board.Mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor()
public class BoardService {

    final BoardRepository boardRepository;
    final BoardMapper boardMapper;

    @Value("${encryptor.key}")
    private String PASSWORD_KEY;

    @Value("${file.dir}")
    private String DIRECTORY_PATH;

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
        return boardRepository.isRealBoard(boardId) == 1;
    }

    public List<CommentDTO> getComments(int boardId){
        if(!isRealBoard(boardId)) return null;
        List<Comment> comments = boardRepository.getComments(boardId);

        return boardMapper.toCommentDtoList(comments);
    }

    /**
     *
     * @param newBoardDTO
     * @return
     * @throws Exception Transactional 어노테이션으로 잘못되면 전체 롤백
     */
    @Transactional
    public int insertBoard(NewBoardDTO newBoardDTO) throws Exception {
        Board board = boardMapper.newBoardToEntity(newBoardDTO);
        int newId = -1;
        try{
            boardRepository.insertBoard(board, PASSWORD_KEY);
            newId = boardRepository.getMaxBoardId();


            List<FileUpload> files = fileDtoToEntity(newBoardDTO.getFiles(), newId);
            if(files != null){
                return newId;
            }
            boardRepository.insertFiles(files);
            saveFiles(newBoardDTO.getFiles());

        } catch (Exception e){
            e.printStackTrace();
            throw new Exception( e.getClass().getName() + ": insertBoard 처리 중 . . ",e);
        }

        return newId;
    }

    private void saveFiles(List<MultipartFile> files) throws Exception {
        try{
            for(MultipartFile file : files){
                if(file.isEmpty()) return;
                file.transferTo(new java.io.File(DIRECTORY_PATH + file.getOriginalFilename()));
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private List<FileUpload> fileDtoToEntity (List<MultipartFile> files, int boardId) {
        if(files==null || files.isEmpty()) return null;
        List<FileUpload> fileEntities = new ArrayList<>();
        for (MultipartFile file : files) {
            if(file.isEmpty()) continue;
            FileUpload newFile = new FileUpload();
            newFile.setName(file.getOriginalFilename());
            newFile.setPath(DIRECTORY_PATH);
            newFile.setUuid(UUID.randomUUID().toString());
            newFile.setBoardId(boardId);
            fileEntities.add(newFile);
        }

        return fileEntities;
    }



    public boolean insertComment(CommentDTO commentDTO) {
        if(!isRealBoard(commentDTO.getBoardId())) return false;
        Comment comment = boardMapper.toComment(commentDTO);
        int res = boardRepository.insertComment(comment);

        return res==1;
    }

    public boolean deleteBoard(int boardId, String password){
        if(!isRealBoard(boardId)) return false;
        if(!isRightPw(boardId, password)) return false;

        return boardRepository.deleteBoard(boardId) == 1;
    }

    private boolean isRightPw(int boardId, String password){
        return boardRepository.checkPassword(boardId, password, PASSWORD_KEY) == 1;
    }
}
