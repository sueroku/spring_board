package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveReqDto {
    private String name;
    private String email;
    private String password;
//    private String role;  //사용자가 String 요청해도 Role클래스 자동형변환
    private Role role; // Role로 두고 그냥 this.role 넣어도 알아서 형변환해주기는 한다.


    public Author toEntity(String encodedPassword){
        Author author = Author.builder()
                .password(encodedPassword)
                .name(this.name) // 순서 상관 없다.
                .email(this.email)
                .posts(new ArrayList<>()) // #071901 해결 두번째시도
                .role(this.role)
                .build();
        return author;
    }


//    public Author toEntity(){
//        Role myRole = null;
//        if(this.role.equals("USER")){ // 이때는 AuthorSaveReq~ 의 role이 String 으로 하는 경우.
//            myRole = Role.USER;
//        }else {
//            myRole = Role.ADMIN;
//        } // 그러나 이게 정석
//        return new Author(this.name, this.email, this.password, myRole);
//    }
}
