package com.project.sul.config;


import com.project.sul.auth.PrincipalOauth2UserService;
import com.project.sul.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MemberService memberService;
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login") //로그인 페이지 url 설정
                .defaultSuccessUrl("/") // 성공시 이동할 url
                .usernameParameter("email") //로그인시 사용할 파라미터 이름으로 email 지정
                .failureUrl("/login") //.로그인 실패시 이동할 url
            .and()
            .logout() //로그아웃 url
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                //패턴과 요청url을 비교하여 일치하는지 판단
                //'members/logout' url 로 요청을 보내면 이를 로그아웃 처리로 인식 하여 로그아웃
                .logoutSuccessUrl("/") //로그아웃 성공시 이동할 url
        ;

        http.oauth2Login()                // OAuth2기반의 로그인인 경우
                .loginPage("/login")        // 인증이 필요한 URL에 접근하면 /login으로 이동
                .defaultSuccessUrl("/")            // 로그인 성공하면 "/" 으로 이동
                .failureUrl("/login")        // 로그인 실패 시 /login으로 이동
                .userInfoEndpoint()            // 로그인 성공 후 사용자정보를 가져온다
                .userService(principalOauth2UserService);    //사용자정보를 처리할 때 사용한다

        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/images/**", "/jquery/**", "/bootstrap/**", "/slick/**", "/payments/**").permitAll()
                .mvcMatchers("/", "/login/**", "/register/**", "/user/**", "/admin/**", "/item/**", "/error/**").permitAll()
//                .mvcMatchers("/admin/**").hasRole("ADMIN")
//                .mvcMatchers("/error").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//                .accessDeniedPage("/error"); //인증되지 않은 사용자가 리소스에 접근했을때 수행되는 핸들러 등록

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //PasswordEncoder 인테페이스 구현체 BCryptPasswordEncoder();
    }


}
