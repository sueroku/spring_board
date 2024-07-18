package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.repository.AuthorRepository;
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
}
