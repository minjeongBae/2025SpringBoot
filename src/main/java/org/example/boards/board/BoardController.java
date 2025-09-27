package org.example.boards.board;

import org.example.boards.board.DTO.BoardDTO;
import org.example.boards.board.DTO.CategoryDTO;
import org.example.boards.board.DTO.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardMapper boardMapper;

    @GetMapping(value = {"/", "list"})
    public String init(Model model) {
        List<BoardDTO> boards = boardMapper.findAllBoards();
        model.addAttribute("boards", boards);

        List<CategoryDTO> categories = boardMapper.findAllCategories();
        model.addAttribute("categories", categories);

        return "/jsp/list.jsp";
    }

    @PostMapping("/board-search")
    public String search(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate headDate
                          , @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tailDate
                          , @RequestParam String searchWord
                          , Model model) {
        List<BoardDTO> boards = boardMapper.searchBoards(headDate, tailDate, searchWord);
        model.addAttribute("boards", boards);

        List<CategoryDTO> categories = boardMapper.findAllCategories();
        model.addAttribute("categories", categories);
        return "/jsp/list.jsp";
    }

    @GetMapping("/view")
    public String view(@RequestParam int boardId, Model model) {
        BoardDTO board = boardMapper.showBoard(boardId);
        model.addAttribute("board", board);

        List<CommentDTO> comments = boardMapper.showComments(boardId);
        model.addAttribute("comments", comments);
        return "/jsp/view.jsp";
    }

    @PostMapping("/view/insert-comment")
    public String insertComment(@RequestParam int boardId,
                                @RequestParam String writer,
                                @RequestParam String content,
                                Model model) {
        CommentDTO comment = new CommentDTO();
        comment.setBoardId(boardId);
        comment.setWriter(writer);
        comment.setContent(content);
        boardMapper.insertComment(comment);

        BoardDTO board = boardMapper.showBoard(boardId);
        model.addAttribute("board", board);

        List<CommentDTO> comments = boardMapper.showComments(boardId);
        model.addAttribute("comments", comments);
        return "/jsp/view.jsp";
    }


    @GetMapping("/write")
    public String write(Model model) {
        List<CategoryDTO> categories = boardMapper.findAllCategories();
        model.addAttribute("categories", categories);

        return "/jsp/write.jsp";
    }
}
