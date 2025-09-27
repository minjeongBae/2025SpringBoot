package org.example.boards.board.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private int boardId;
    private int commentId;
    private String content;
    private String writer;
    private String createDate;
}