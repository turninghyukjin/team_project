package com.project.sul.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
}
//엔티티의 일자 관리를 자동화
//엔티티의 생성일자와 수정일자를 추적