package com.beyond.board.common;

import com.beyond.board.author.service.LoginSuccessHandler;
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
                .authorizeRequests()
                    .antMatchers("/", "/author/create", "/author/login-screen") // antMatchers 는 인증 제외
                    .permitAll()
//                그외 요청은 모두 인증 필요
                    .anyRequest().authenticated()
                .and()
//                만약 세션로그인이 아니라, 토큰로그인일 경우
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .formLogin()
                    .loginPage("/author/login-screen")
                    .loginProcessingUrl("/doLogin") // dologin메서드는 스프링에서 미리 구현
//                        다만, dologin에 넘겨줄 email, password 속성명은 별도 지정
                        .usernameParameter("email")
                        .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())
                .and()
                    .logout() // security에서 구현된 dologout기능 그대로 사용
                    .logoutUrl("/doLogout")
                .and()
                .build(); // 원하는 것만 세팅해도 ok 빌더패턴이라
    }
}
