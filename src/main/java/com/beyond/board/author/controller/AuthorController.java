package com.beyond.board.author.controller;

import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@Slf4j
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

    @GetMapping("/author/login-screen")
    public String authorLogin(){
        return "author/login-screen";
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
//        log.info("get요청, parameter 는" + id);
//        log.info("method명 : authorDetail");
        model.addAttribute("author", authorService.authorDetail(id));
        return "author/author_detail";
    }

//    @DeleteMapping("/author/delete/{id}") // 정석(axios)대로 한다면...
    @GetMapping("/author/delete/{id}")
    public String authorDelete(@PathVariable Long id){
        authorService.authorDelete(id);
        return "redirect:/author/list";
    }

    @PostMapping("/author/update/{id}")
    public String authorUpdate(@PathVariable Long id, @ModelAttribute AuthorUpdateReqDto dto, Model model){
//        model.addAttribute("author",authorService.authorUpdate(id,dto));
        authorService.authorUpdate(id,dto);
        return "redirect:/author/detail/"+id;
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
