package org.example.boards.board.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardDTO {
    private int boardId;
    private String title;
    private String category;
    private String writer;
    private int views;
    private String createDate;
    private String updateDate;
    private String content;
    private int files;
}
