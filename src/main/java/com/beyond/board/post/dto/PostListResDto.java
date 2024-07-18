package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostListResDto {
    private Long id;
    private String title;
//    private Author author; // author객체 그 자체를 return하게 되면 Author안에 Post가 재참조되어,
//    순환침조 이슈 발생. // 결국 그대로는 쓸 수 없다... 새로운 클래스(dto)만들어서 쓰든 해야한다....
    private String author_email;

}
