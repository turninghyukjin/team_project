package com.project.sul.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class RvMvcConfig implements WebMvcConfigurer {
    @Value("${reviewPath}")
    String reviewPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/review/**") // url이 review로 시작하면 uploadPath 설정 폴더기준으로 가져오기
                .addResourceLocations(reviewPath);
    }
}
