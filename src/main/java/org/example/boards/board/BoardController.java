package org.example.boards.board;

import lombok.RequiredArgsConstructor;
import org.example.boards.board.DTO.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor()
public class BoardController {

    final BoardService boardService;

    // TODO 페이징
    @GetMapping(value = {"/", "list"})
    public String init(@ModelAttribute SearchKeyDTO searchKey,
                       Model model) {
        List<BoardListDTO> boards = boardService.getBoards(searchKey);
        model.addAttribute("boards", boards);

        List<CategoryDTO> categories = boardService.getCategories();
        model.addAttribute("categories", categories);

        return "/jsp/list.jsp";
    }

    @GetMapping(value = "view")
    public String view(@RequestParam String boardId,
                       Model model) {
        BoardViewDTO boardViewDTO = boardService.getBoardView(boardId);
        List<CommentDTO> comments = boardService.getComments(boardId);

        if(boardViewDTO==null || comments==null) {
            model.addAttribute("errorMsg", "존재하지 않는 게시물입니다.");
        } else {
            model.addAttribute("board", boardViewDTO);
            model.addAttribute("comments", comments);
        }

        return "/jsp/view.jsp";
    }

    @PostMapping("view/insert-comment")
    public String insertComment(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("댓글달기");
        boardService.insertComment(commentDTO);

        return "redirect:/view?boardId=" + commentDTO.getBoardId();
    }
}
