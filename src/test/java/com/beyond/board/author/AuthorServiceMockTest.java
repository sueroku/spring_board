package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthorServiceMockTest {


    @Autowired
    private AuthorService authorService;

    @Test
    public void findAuthorDetailTest(){

    }

//    저장 및 디테일 조회

//    업데이트 검증

//    findAll 검증


}
