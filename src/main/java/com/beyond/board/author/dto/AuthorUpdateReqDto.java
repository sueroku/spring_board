package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor // 필요 없어용
public class AuthorUpdateReqDto {
    private String name;
    private String password;

//    public Author toEntity(){
//        Author author = Author.builder()
//                .password(this.password)
//                .name(this.name) // 순서 상관 없다.
//                .build();
//        return author;
//    }
}
