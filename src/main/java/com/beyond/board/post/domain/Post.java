package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 3000)
    private String contents;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    public PostListResDto listFromEntity(){
        return new PostListResDto().builder()
                .id(this.id).title(this.title).author_email(this.author.getEmail()).build();
//        return new PostListResDto().builder()
//                .id(this.id).title(this.title).author(this.author).build();
    }

    public PostDetResDto detFromEntity(){
//        LocalDateTime localDateTime = this.getCreatedTime();
//        String date = localDateTime.getYear() + "년" + localDateTime.getMonthValue() + "월" + localDateTime.getDayOfMonth() + "일";
//        return new PostDetResDto(this.id, this.title, this.contents, this.author.getEmail(), this.createdTime, this.getUpdateTime());
    return PostDetResDto.builder().id(this.id).title(this.title).contents(this.contents).author_email(this.author.getEmail()).createdTime(this.getCreatedTime()).updateTime(this.getUpdateTime()).build();
    }
}
