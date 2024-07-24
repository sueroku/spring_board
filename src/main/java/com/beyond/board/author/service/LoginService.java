package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private AuthorService authorService; // 장점만 많ㅇ느 생성자 패턴이 실무에는 맞아 응응 지금처럼 간단히 필드주입도뭐..

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorService.authorFindByEmail(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + author.getRole().toString())); // "ROLE_" + 넣어줘야한데.. // toString 안넣어도 된디야
        return new User(author.getEmail(), author.getPassword(), authorityList);
    }
}
