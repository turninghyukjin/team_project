package com.project.sul.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if(authentication != null){
            userId = authentication.getName();
        }
        return Optional.of(userId);
    }

}
//AuditorAware 인터페이스는 Spring Data JPA에서
// 자동으로 생성일자와 수정일자를 추적하기 위해 사용되는 인터페이스입니다.
//이 인터페이스는 현재 사용자를 나타내는 정보를 제공하는 메서드를 정의합니다.

//보통 getCurrentAuditor() 메서드를 구현하여 현재 인증된 사용자의 정보를 반환하도록 설정합니다.
// 예를 들어, Spring Security를 사용하여 사용자 인증을 처리하는 경우,
// 현재 사용자의 정보를 SecurityContext에서 가져와 반환할 수 있습니다.

//SecurityContextHolder를 사용하여 현재 인증된 사용자의 정보를 가져옵니다.
// 인증된 사용자가 없거나 인증되지 않은 경우에는 Optional.empty()를 반환하고,
// 그렇지 않은 경우에는 사용자의 이름을 Optional로 감싸서 반환합니다.