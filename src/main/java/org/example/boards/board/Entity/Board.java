package org.example.boards.board.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    /* TODO 용도에 따라 분리
    // TODO 작명 주의 !!
    // TODO @schema 알아보기
    // TODO @Valid / @NotNull
       TODO error Handler

    */
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
