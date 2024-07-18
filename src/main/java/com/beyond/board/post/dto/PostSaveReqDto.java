package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveReqDto {
    private String title;
    private String contents;
    private String email; // 추후 로그인 기능 이후에는 없어질 dto

    public Post toEntity(Author author){
        Post post = Post.builder().title(this.title).contents(this.contents).author(author).build();
        return post;
    }

}
