package org.example.boards.board.Entity;

import lombok.Data;

@Data
public class Comment {
    private int boardId;
    private int commentId;
    private String content;
    private String writer;
    private String createDate;
}