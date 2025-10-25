package org.example.boards.board.DTO;

import lombok.Data;

@Data
public class BoardViewDTO {
    private int boardId;
    private String title;
    private String category;
    private String writer;
    private int views;
    private String createDate;
    private String updateDate;
    private String content;
    private String password;
}
