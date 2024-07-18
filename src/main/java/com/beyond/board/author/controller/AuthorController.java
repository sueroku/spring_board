package com.beyond.board.author.controller;

import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author/create")
    public String authorCreate(@RequestBody AuthorSaveReqDto dto){
        authorService.authorCreate(dto);
        return "ok";
    }

    @GetMapping("/author/list")
    public List<AuthorListResDto> authorList(){
        return authorService.authorList();
    }

    @GetMapping("/author/detail/{id}")
    public AuthorDetResDto authorDetail(@PathVariable Long id){
        return authorService.authorDetail(id);
    }
}
