package com.beyond.board.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateReqDto {
    private String title;
    private String contents;

//    업데이트타임은 업데이트 안해주나
}
