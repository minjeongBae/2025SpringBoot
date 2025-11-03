package org.example.boards.board.Entity;

import lombok.Data;

/**
 * 테이블명: File
 * 자바표준라이브러리 File 클래스와 이름 충돌
 * FileData로 지정함
 */
@Data
public class FileData {
    private String name;
    private String path;
    private String uuid;
    private int boardId;
}

