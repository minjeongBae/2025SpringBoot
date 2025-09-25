package org.example.boards.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardMapper boardMapper;

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDTO> list = boardMapper.findAllBoards();
        model.addAttribute("list", list);
        return "/jsp/list.jsp";
    }

    @GetMapping("/view")
    public String view() {
        return "/jsp/view.jsp";
    }
}
