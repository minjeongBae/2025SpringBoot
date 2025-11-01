package org.example.boards.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.boards.board.DTO.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

        return "/jsp/list.jsp";
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

        return "/jsp/view.jsp";
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
     * @param password 삭제를 위한 체크값
     * @param model
     * @return
     */
    @PostMapping("view/delete")
    public String deleteBoard(@RequestParam int boardId,
                              @RequestParam String password,
                              Model model) {
        boolean res = boardService.deleteBoard(boardId, password);

        if(!res) {}

        return "redirect:/view?boardId=" + boardId;
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

        return "/jsp/write.jsp";
    }

    /**
     * 게시물 등록 후 등록된 게시물 페이지 반환
     * @param newBoardDTO
     * @return
     */
    @PostMapping("/insert-board")
    public String insertBoard (@Valid @ModelAttribute NewBoardDTO newBoardDTO,
                               Model model) {

        // 게시물 등록 후 받은 id
        int boardId = boardService.insertBoard(newBoardDTO);

        if(boardId==-1) {
            model.addAttribute("errorMsg", "실패했습니다.");
            return "/jsp/write.jsp";
        }

        return view(boardId, model);
    }

}
