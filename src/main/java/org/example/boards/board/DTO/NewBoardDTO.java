package org.example.boards.board.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewBoardDTO
{
    // 게시물 등록을 위함
    @NotNull
    String title;
    @NotNull
    String password;

    String writer;
    int category;

    @NotNull
    String content;

    MultipartFile firstFile;
    MultipartFile secondFile;
    MultipartFile thirdFile;
}
