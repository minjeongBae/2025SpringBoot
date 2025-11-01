package org.example.boards.board.Entity;

import lombok.Data;

@Data
public class FileUpload {
    private String name;
    private String path;
    private String uuid;
    private int boardId;
}

