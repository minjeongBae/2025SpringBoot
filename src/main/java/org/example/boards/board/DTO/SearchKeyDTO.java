package org.example.boards.board.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SearchKeyDTO {
    private String word;
    private Date headDate;
    private Date tailDate;
}
