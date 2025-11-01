package org.example.boards.board.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDTO {

    private int boardId;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    private String createDate;
}
