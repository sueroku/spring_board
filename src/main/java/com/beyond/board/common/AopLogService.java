package com.beyond.board.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect // AOP 코드임을 암시
@Component
@Slf4j
public class AopLogService {
//
    @Pointcut("within(@org.springframework.stereotype.Controller *)") //모든 컨트롤러 어노테이션 대상
    public void controllerPointCut(){
    }





    @Around("controllerPointCut()")
    public Object controllerLogger(ProceedingJoinPoint joinPoint){
        log.info("aop start");
        log.info("method명 : " + joinPoint.getSignature().getName());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("HTTP 메서드" + request.getMethod());
        Map<String, String[]> parameterMap = request.getParameterMap(); // 그냥 쓰면 주소로 나오넹
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.valueToTree(parameterMap);
        log.info("user inputs : " + objectNode);

//        Map<String, String[]> parameterMap = request.getParameterMap();
//        log.info("user inputs:" + parameterMap); // 주소로 나옴
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode objectNode = objectMapper.valueToTree(parameterMap);// value를 tree로
//        log.info("user inputssss: " + objectNode);


        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        log.info("aop end");
        return result;
    }






////    방법1. around를 통해 controller와 걸쳐져 있는 사용 패턴
//    @Around("controllerPointCut()")
//    public Object controllerLogger(ProceedingJoinPoint joinPoint){
//        log.info("aop start");
//        log.info("method명 : " + joinPoint.getSignature().getName());
////        직접 HttpServletRequest객체(사용자요청정보가 들어있는 객체)를 꺼내는 법
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("HTTP 메서드" + request.getMethod()); // 매게변수며, 메소드며 다 들어 있어용
//        Object result = null;
//        try {
//            result = joinPoint.proceed(); // 사용자가 실행하고자 하는 코드 실행부
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//        log.info("aop end");
//        return result;
//    }


////    방법2. before, after 어노테이션 사용
//    @Before("controllerPointCut()")
//    public void beforeController(JoinPoint joinPoint){
//        log.info("aop start");
//        log.info("method명 : " + joinPoint.getSignature().getName());
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("HTTP 메서드" + request.getMethod());
//    }
//
//
//    @After("controllerPointCut()")
//    public void afterController(){
//        log.info("aop end");
//    }
}
