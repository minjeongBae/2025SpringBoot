package org.example.boards.board.DTO;

import lombok.Data;

@Data
public class CommentDTO {
    private int boardId;
    private String content;
    private String createDate;
}
