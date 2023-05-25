package com.project.sul.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")  //프로 퍼티값을 읽어옵니다.
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);//로컬컴퓨터에 저장된 파일을 읽어올 root경로 설정
    }
    //addResourceHandlers 로컬 컴퓨터에 저장된 파일을 잃어오는 root 경로를 
    //설정
    
    //웹브라우저에 입력하는  url에 /images로 시작하는 경우 uploadPath에 설정한
    // 폴더를 기준으로 파일을 읽어 오도록 설정합니다.
}
// file:// 파일 프로 토콜을 나타내며 - scheme://host/path
// c:/shop 파일 시스템 경로
// uploadPath=file:///C:/shop/
