//package com.project.sul.config;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class CustomExceptionHandler {
//    @ExceptionHandler(AccessDeniedException.class)
//    public String handleAccessDeniedException(AccessDeniedException ex) {
//        // 처리 로직 작성
//        return "error-page"; // 에러 페이지로 이동하거나 적절한 응답을 반환하도록 처리
//    }
//}