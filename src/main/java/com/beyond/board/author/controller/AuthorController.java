package com.beyond.board.author.controller;

import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/create")
    public String authorCreate(){
        return "author/author_register";
    }
    @PostMapping("/author/create")
    public String authorCreatePost(@ModelAttribute AuthorSaveReqDto dto){ // 모를때는 디버깅기능 좀 써라.. 뭐가 들어오는지 보인다.
        try {
            authorService.authorCreate(dto);
//            return "redirect:/author/list";
            return "redirect:/";
        }catch (IllegalArgumentException e){
//            model.addAttribute()
            return "error!";
        }
    }

    @GetMapping("/author/list")
    public String authorList(Model model){
        List<AuthorListResDto> authorListResDtos = authorService.authorList();
        model.addAttribute("authorList", authorListResDtos);
        return "author/author_list";
    }

    @GetMapping("/author/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model){
//        AuthorDetResDto authorDetResDto = authorService.authorDetail(id);
        model.addAttribute("author", authorService.authorDetail(id));
        return "author/author_detail";
    }


//    @PostMapping("/author/create")
//    public String authorCreate(@RequestBody AuthorSaveReqDto dto){
//        authorService.authorCreate(dto);
//        return "ok";
//    }
//
//    @GetMapping("/author/list")
//    public List<AuthorListResDto> authorList(){
//        return authorService.authorList();
//    }
//
//    @GetMapping("/author/detail/{id}")
//    public AuthorDetResDto authorDetail(@PathVariable Long id){
//        return authorService.authorDetail(id);
//    }
}
