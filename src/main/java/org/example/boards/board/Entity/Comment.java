package org.example.boards.board.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private int boardId;
    private int commentId;
    private String content;
    private String writer;
    private String createDate;
}