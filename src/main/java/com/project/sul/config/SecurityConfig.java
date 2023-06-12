package com.project.sul.config;


import com.project.sul.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@SuppressWarnings("deprecation")
public class SecurityConfig {
    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/user/login") //로그인 페이지 url 설정
                .defaultSuccessUrl("/") // 성공시 이동할 url
                .usernameParameter("email") //로그인시 사용할 파라미터 이름으로 email 지정
                .failureUrl("/user/login/error") //.로그인 실패시 이동할 url
                .and()
                .logout() //로그아웃 url
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                //패턴과 요청url을 비교하여 일치하는지 판단
                //'members/logout' url 로 요청을 보내면 이를 로그아웃 처리로 인식 하여 로그아웃
                .logoutSuccessUrl("/") //로그아웃 성공시 이동할 url
        ;
        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/images/**", "/jquery/**", "/bootstrap/**", "/slick/**","/payments/**").permitAll()
                .mvcMatchers("/", "/login/**", "/register/**","/admin/**").permitAll()
//                .mvcMatchers("/admin/**").hasRole("ADMIN")
//                .mvcMatchers("/KcpPayment").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ; //인증되지 않은 사용자가 리소스에 접근했을때 수행되는 핸들러 등록

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //PasswordEncoder 인테페이스 구현체 BCryptPasswordEncoder();
    }





}
