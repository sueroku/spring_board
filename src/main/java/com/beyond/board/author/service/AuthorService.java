package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true) //  조회작업시에 readOnly 설정하면 성능 향상 /// 다만, 저장 작업시에는 Transactional필요
public class AuthorService {

    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author authorCreate(AuthorSaveReqDto dto){
        if(authorRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("It's alreadt existed.");
        }
        Author author = dto.toEntity();
//        cascade persist 테스트 remove 테스트는 회원삭제로 대체.
//        author.getPosts().add(Post.builder().title("가입인사").contents("안녕하세요."+dto.getName()+"입니다.").build()); // postRepo(.save()하지도 않음) 접근도 안했는데 해준다. == cascade => 안되는 뎁쇼...?#071901
        author.getPosts().add(Post.builder().title("가입인사").author(author).contents("안녕하세요."+dto.getName()+"입니다.").build());// #071901 해결 두번째시도
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor;
    }

    public List<AuthorListResDto> authorList(){
        List<AuthorListResDto> authorResDtos = new ArrayList<>();
        List<Author> authors = authorRepository.findAll();
        for(Author a : authors){
            authorResDtos.add(a.listFromEntity());
        }
        return authorResDtos;
    }

    public AuthorDetResDto authorDetail(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Author is not found!"));
        return new AuthorDetResDto().fromEntity(author);
    }

    public Author authorFindByEmail(String email){
        Author author = authorRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("emmail is not found!"));
        return author; // post에 author넣을래 ver1
    }

    @Transactional
    public void authorDelete(Long id){
//        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Author is not found!"));
//        authorRepository.delete(author);
        authorRepository.deleteById(id);
    }

    @Transactional
    public void authorUpdate(Long id, AuthorUpdateReqDto dto){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Author is not found!"));
//        author = dto.toEntity(); // 아 새로 생기는 구나 실수 ><
//        <수정 데이터가 원래 데이터에 비해 변경이 안되었거나, 수정 값이 안들어오는 경우!!!!!>
//        1. 이름과 비밀번호 각각 변경했는지 안했는지 db의 값과 비교 == 초큼 짜치네..
//        2. (변경을 전부 했다치고???) 암튼 null 값이 들어가면 안되잖아. 수정할 때 원래의 값을 value(in html)에 일단 넣어놔...
        author.updateAuthor(dto);
//        authorRepository.save(author); // save 필수 아니에용 why? jpa가 특정 엔티티의 변경을 자동으로 인지하고 변경사항을 db에 반영하는 것이 dirtychecking(변경감지)
//        return new AuthorDetResDto().fromEntity(author); // dirtychecking(변경감지)시 @Transactional 필요
    }
}
