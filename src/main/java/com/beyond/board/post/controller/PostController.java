package com.beyond.board.post.controller;

import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import com.beyond.board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostController {

    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/create")
    public String postCreate(){
        return "post/post_register";
    }
    @PostMapping("/post/create")
    public String postCreatePost(@ModelAttribute PostSaveReqDto dto){
        postService.postCreate(dto);
        return "redirect:/post/list";
    }

//    @GetMapping("/post/list")
//    public String postList(Model model){
//        model.addAttribute("postList", postService.postList());
//        return "post/post_list";
//    }

    @GetMapping("/post/list")
    public String postList(Model model, @PageableDefault(size = 5, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("postList", postService.postList(pageable));
        return "post/post_list";
    }

    @GetMapping("/post/list/page")
    @ResponseBody
//    Pageble 요청 방법 : localhost:8080/post/list/page?size=10&page=0
    public Page<PostListResDto> postListTes(@PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){ // 이건 관리자가 정한 디폴트 값, 사용자가 값을 전달하면 그 값으로 덮어쓴다.
        return postService.postListPage(pageable); // 모든 페이지 정보를 끌고 오는게 아니라 사용자가 보는 몇번페이지의 값들의 목록만 들고 온다.
    }

    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model){
        model.addAttribute("post", postService.postDetail(id));
        return "post/post_detail";
    }

    @GetMapping("/post/delete/{id}")
    public String postDelete(@PathVariable Long id){
        postService.postDelete(id);
        return "redirect:/post/list";
    }

    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Long id, @ModelAttribute PostUpdateReqDto dto){
        postService.postUpdate(id,dto);
        return "redirect:/post/detail/"+id;
    }



//    @PostMapping("/post/create")
//    public String postCreatePost(@RequestBody PostSaveReqDto dto){
//        postService.postCreate(dto);
//    }
//
//    @GetMapping("/post/list")
//    public List<PostListResDto> postList(Model model){
//        return postService.postList();
//    }
//
//    @GetMapping("/post/detail/{id}")
//    public PostDetResDto postDetail(@PathVariable Long id){
//        return postService.postDetail(id);
//    }
}
