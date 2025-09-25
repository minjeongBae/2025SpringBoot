package org.example.boards;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.boards.board")
public class BoardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardsApplication.class, args);
    }

}
