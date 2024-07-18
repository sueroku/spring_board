package com.beyond.board.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor // 빌더를 위해..


@NoArgsConstructor
@Data // 파싱한다든지.. 그럴때..
public class AuthorListResDto {
    private Long id;
    private String name;
    private String email;
}
