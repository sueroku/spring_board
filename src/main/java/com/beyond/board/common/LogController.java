package com.beyond.board.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j //@1 slf4j어노테이션을 통한 로거 선언 방법
public class LogController {
//    slf4j어노테이션 또는 Logger 직접선언


//    로거 직접선언 방법 @2
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);


    @GetMapping("log/test1")
    public String logTest1(){
//        기존의 로그 방식 System.out.println
//        문제접1.성능이 좋지 않음 2. 로그 분류 작업 불가 (정상, 저장, 에러...) // 항상 출력 -> 너무 많은 로그..
        System.out.println("println 로그입니다.");
        logger.debug("debug 로그입니다."); //@2 디버깅 모드일 때 // 설정한 로그레벨 이상만 출력이 된다.
        logger.info(("info 로그입니다.")); //@2 기본적으로 info모드
        logger.error("error 로그입니다."); //@2
//        log.info(""); // @1
        return "ok";
    }
}
