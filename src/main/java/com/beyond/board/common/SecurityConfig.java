package com.beyond.board.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //  spring security 설정을 customizing하기 위함.
@EnableGlobalMethodSecurity(prePostEnabled = true) // pre : 사전    post : 사후     인증 검사
public class SecurityConfig {

    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception { // 인터페이스      구현체....
        return httpSecurity
                .csrf().disable() // csrf공격(세션과 쿠키를 이용한 공격)에 대한 설정은 하지 않겠다라는 의미 (mvc패턴(주로 쿠키, 세션 사용한다.)에서는 막아줘야해)

                .build(); // 원하는 것만 세팅해도 ok 빌더패턴이라
    }
}
