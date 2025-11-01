package org.example.boards.board.Entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Board {
    /*
       TODO @schema 알아보기
       TODO @Valid / @NotNull
       TODO error Handler
    */

   // private final static String KEY = "PASSWORD"; // 비밀번호 암호화 키

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
