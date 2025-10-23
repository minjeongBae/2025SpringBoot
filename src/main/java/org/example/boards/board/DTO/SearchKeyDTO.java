package org.example.boards.board.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
public class SearchKeyDTO {
    private String word;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate headDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tailDate;

    private int category;
}
