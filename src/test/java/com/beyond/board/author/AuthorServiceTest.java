package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
@Transactional
//@Rollback
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

//    저장 및 detail 조회
    @Test
    void saveAndFind(){
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("hong", "hong@daum.net","aa1234567890", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);
//        Author authorDetail = authorService.authorFindByEmail("hong@daum.net"); // 이건 좀 그래.. 이메일로 찾는거..
        Author authorDetail = authorRepository.findById(author.getId()).orElseThrow(()->new EntityNotFoundException("Author is not found!"));
//        Assertions.assertEquals(author.getEmail(), authorDto.getEmail());
        Assertions.assertEquals(authorDetail.getEmail(), authorDto.getEmail());
    }


//    update 검증
    @Test
    void updateAndFind(){
//    객체 create
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("hong", "hong@daum.net","aa1234567890", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);
//    수정작업 (name, password)
        authorService.authorUpdate(author.getId(), new AuthorUpdateReqDto("honghong", "hong123456789"));
//    수정 후 재 죄회 : name password 가 각각 맞는지 검증
//        Author savedAuthor = authorService.authorFindByEmail("hong@daum.net");
        Author savedAuthor = authorRepository.findById(author.getId()).orElseThrow(()->new EntityNotFoundException("Author is not found!"));
        Assertions.assertEquals(savedAuthor.getName(), "honghong");
        Assertions.assertEquals(savedAuthor.getPassword(), "hong123456789"); // 맞는거
//        Assertions.assertEquals(savedAuthor.getPassword(), "hong1234567"); // 틀려본거


////    내가 한거!
////    객체 create
//        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("hong", "hong@daum.net","aa1234567890", Role.ADMIN);
//        authorService.authorCreate(authorDto);
//        Author author = authorService.authorFindByEmail("hong@daum.net");
////    수정작업 (name, password)
//        AuthorUpdateReqDto authorUpdateReqDto = new AuthorUpdateReqDto("honghong", "hong123456789");
//        authorService.authorUpdate(author.getId(), authorUpdateReqDto);
////    수정 후 재 죄회 : name password 가 각각 맞는지 검증
//        Assertions.assertEquals(author.getName(), authorUpdateReqDto.getName());
//        Assertions.assertEquals(author.getPassword(), authorUpdateReqDto.getPassword());
    }


//    findAll 검증
    @Test
    public void findAllTest(){
//      기존 사이즈 구해놔
        int size = authorService.authorList().size();
//        3개 author 객체 저장
        AuthorSaveReqDto authorDto1 = new AuthorSaveReqDto("hong1", "hong1@daum.net","aa1234567890", Role.ADMIN);
        AuthorSaveReqDto authorDto2 = new AuthorSaveReqDto("hong2", "hong2@daum.net","aa1234567890", Role.USER);
        AuthorSaveReqDto authorDto3 = new AuthorSaveReqDto("hong3", "hong3@daum.net","aa1234567890", Role.ADMIN);
        authorService.authorCreate(authorDto1);
        authorService.authorCreate(authorDto2);
        authorService.authorCreate(authorDto3);
//        authorList를 조회한 후, 저장한 개수와 저장된 개수 비교
        List<AuthorListResDto> authorList = authorService.authorList();
        Assertions.assertEquals(authorList.size(), size+3);


////        내가 한거!
////        3개 author 객체 저장
//        AuthorSaveReqDto authorDto1 = new AuthorSaveReqDto("hong1", "hong1@daum.net","aa1234567890", Role.ADMIN);
//        AuthorSaveReqDto authorDto2 = new AuthorSaveReqDto("hong2", "hong2@daum.net","aa1234567890", Role.USER);
//        AuthorSaveReqDto authorDto3 = new AuthorSaveReqDto("hong3", "hong3@daum.net","aa1234567890", Role.ADMIN);
//        authorService.authorCreate(authorDto1);
//        authorService.authorCreate(authorDto2);
//        authorService.authorCreate(authorDto3);
////        authorList를 조회한 후, 저장한 개수와 저장된 개수 비교
//        List<AuthorListResDto> authorList = authorService.authorList();
//        Assertions.assertEquals(authorList.size(), 3);
    }


}
