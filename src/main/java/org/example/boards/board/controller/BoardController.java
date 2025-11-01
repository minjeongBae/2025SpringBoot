package org.example.boards.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.boards.board.BoardService;
import org.example.boards.board.DTO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor()
public class BoardController {

    final BoardService boardService;

    // TODO paging

    /**
     *
     * @param searchKey : 검색조건입니다 (날짜, 검색어)
     * @param model
     * @return 초기화면 (목록화면)
     */
    @GetMapping(value = {"/", "list"})
    public String init(@ModelAttribute SearchKeyDTO searchKey,
                       Model model) {
        List<BoardListDTO> boards = boardService.getBoards(searchKey);
        model.addAttribute("boards", boards);

        List<CategoryDTO> categories = boardService.getCategories();
        model.addAttribute("categories", categories);

        return "/jsp/list";
    }

    /**
     *
     * @param boardId : 게시물 ID (BOARD.PK)
     * @param model
     * @return 단일 게시물 페이지
     */
    @GetMapping(value = "view")
    public String view(@RequestParam int boardId,
                       Model model) {
        BoardViewDTO boardViewDTO = boardService.getBoardView(boardId);
        List<CommentDTO> comments = boardService.getComments(boardId);

        if(boardViewDTO==null || comments==null) {
            model.addAttribute("errorMsg", "존재하지 않는 게시물입니다.");
        } else {
            model.addAttribute("board", boardViewDTO);
            model.addAttribute("comments", comments);
        }

        return "/jsp/view";
    }

    /**
     *
     * @param commentDTO : 댓글
     * @param bindingResult : valid 후 결과
     * @param redirectAttributes : 리다이렉트 시 에러문구를 전송을 위함
     * @return 댓글달기 위함
     */
    @PostMapping("view/insert-comment")
    public String insertComment(@Valid @ModelAttribute CommentDTO commentDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMsg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            boolean res = boardService.insertComment(commentDTO);
            if(!res) {
                redirectAttributes.addFlashAttribute("errorMsg", "정상적으로 등록되지 않았습니다.");
            }
        }

        return "redirect:/view?boardId=" + commentDTO.getBoardId();
    }

    /**
     *
     * @param boardId 삭제할 게시물 ID (PK)
     * @return
     */
    @GetMapping("/delete-board")
    public String inputForDeleteBoard(@RequestParam int boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "/jsp/delete";
    }

    @PostMapping("/delete-board")
    public String deleteBoard(@RequestParam int boardId,
                              @RequestParam String password,
                              Model model) {
        boolean res = boardService.deleteBoard(boardId, password);
        if(res) {
            model.addAttribute("msg", "게시글이 삭제되었습니다.");
        }else {
            model.addAttribute("msg", "삭제를 실패하였습니다.");
        }
        return "/jsp/delete";


    }

    /**
     * 게시물 등록을 위한 input 페이지
     * @param model
     * @return
     */
    @GetMapping("/write")
    public String inputForInsertBoard(Model model) {

        List<CategoryDTO> categories = boardService.getCategories();
        model.addAttribute("categories", categories);

        return "/jsp/write";
    }

    /**
     * 게시물 등록 후 등록된 게시물 페이지 반환
     * @param newBoardDTO
     * @return
     */
    @PostMapping("/insert-board")
    public String insertBoard (@Valid @ModelAttribute NewBoardDTO newBoardDTO,
                               BindingResult bindingResult,
                               Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new IOException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        try{
            int boardId = boardService.insertBoard(newBoardDTO);
            return view(boardId, model);
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": boardService.insertBoard() 처리 중 Exception 발생" );
        }
    }

}
