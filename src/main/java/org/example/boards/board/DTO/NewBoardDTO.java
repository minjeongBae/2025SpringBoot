package org.example.boards.board.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewBoardDTO
{
    @NotNull(message = "제목은 필수값입니다.")
    String title;
    @NotNull(message = "비밀번호는 필수값입니다.")
    String password;
    @NotNull(message = "내용은 필수값입니다.")
    String content;

    String writer;
    int category = 0;
    List<MultipartFile> files = new ArrayList<>();
}
