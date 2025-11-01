package org.example.boards.board.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.example.boards.board.BoardService;
import org.example.boards.board.DTO.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.io.IOException;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor()
public class ExceptionController {

    final BoardService boardService;

    @ExceptionHandler(value= IOException.class)
    public String inputBoardIOException(Exception e, Model model){
        List<CategoryDTO> categories = boardService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("errorMsg",e.getMessage());

        return "/jsp/write";
    }

    @ExceptionHandler(value= Exception.class)
    public String inputBoardException(Exception e, Model model){
        List<CategoryDTO> categories = boardService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("errorMsg",e.getMessage());

        return "jsp/write";
    }
}
