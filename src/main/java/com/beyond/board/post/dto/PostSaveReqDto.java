package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveReqDto {
    private String title;
    private String contents;
    private String email; // 추후 로그인 기능 이후에는 없어질 dto

    private String appointment;
    private String appointmentTime;


    public Post toEntity(Author author, LocalDateTime appointMentTime){
        Post post = Post.builder().title(this.title).contents(this.contents)
                .appointment(this.appointment).appointmentTime(appointMentTime).author(author).build();
        return post;
    }


//    public Post toEntity(Author author){
//        Post post = Post.builder().title(this.title).contents(this.contents).author(author).build();
//        return post;
//    } // 그 전 버전


//   내가 시도
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime appointmentTime; // 시도


//    public Post toEntity(Author author){
////        if(this.appopintment.equals("Y")){
////
////            Post post = Post.builder().title(this.title).contents(this.contents)
////                    .appointment(this.appopintment).appointmentTime().author(author).build();
////            return post;
////        }else {
////            Post post = Post.builder().title(this.title).contents(this.contents)
////                    .appointment(this.appopintment).author(author).build();
////            return post;
////        } // 시도
//
//
//        Post post = Post.builder().title(this.title).contents(this.contents)
//                .appointment(this.appopintment).author(author).build();
//        return post;
//    }
}
