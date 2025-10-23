package org.example.boards.board;

import lombok.RequiredArgsConstructor;
import org.example.boards.board.DTO.BoardDTO;
import org.example.boards.board.DTO.CategoryDTO;
import org.example.boards.board.DTO.SearchKeyDTO;
import org.example.boards.board.Entity.Board;
import org.example.boards.board.Entity.Category;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor()
public class BoardController {

    final BoardService boardService;

    // TODO 페이징
    @GetMapping(value = {"/", "list"})
    public String init(@ModelAttribute SearchKeyDTO searchKey,
                       Model model) {
        List<BoardDTO> boards = boardService.getBoards(searchKey);
        model.addAttribute("boards", boards);

        List<CategoryDTO> categories = boardService.getCategories();
        model.addAttribute("categories", categories);

        return "/jsp/list.jsp";
    }
}
