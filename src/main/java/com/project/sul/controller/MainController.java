package com.project.sul.controller;

import com.project.sul.auth.PrincipalDetails;
import com.project.sul.constant.Role;
import com.project.sul.dto.*;
import com.project.sul.entity.Member;
import com.project.sul.repository.MemberRepository;
import com.project.sul.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/")
    public String main(HttpSession session) {
        String nickname = (String) session.getAttribute("nickname");
        return "pages/main/main";
    }

    @PostMapping(value = "login/email")
    public String loginSucces(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes,HttpSession session) {
        UserDetails userDetails = memberService.loadUserByUsername(email);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            // 비밀번호가 일치하지 않을 경우 처리
            return "redirect:/login?error";
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 필요한 동작을 수행한 후 로그인 결과에 따라 적절한 응답을 반환.

        PrincipalDetails principalDetails = (PrincipalDetails) userDetails;
        String nickname = principalDetails.getNickname();
        session.setAttribute("nickname", nickname);
        redirectAttributes.addFlashAttribute("nickname", nickname);


        log.info("닉네임: {}", nickname);
        return "redirect:/"; // 로그인 후 이동할 페이지.
    }

    @GetMapping(value = "register/agreement")
    public String   agreement() {
        return "pages/main/agreement";
    }

    @GetMapping(value = "register")
    public String register() {
        return "pages/main/register";
    }

    @GetMapping(value = "register/email")
    public String registerEmail() {
        return "pages/main/register_email";
    }


    // form 로그인 시
//    @GetMapping(value = "/form/loginInfo")
//    @ResponseBody
//    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//
//        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        Member member = principal.getMember();
//        System.out.println(member);
//
////        Member member1 = principalDetails.getMember();
////        System.out.println(member1);
//
//        return member.toString();
//    }

    // oauth2 로그인 시
    @GetMapping(value = "/oauth/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        // PrincipalOauth2UserService의 getAttributes내용과 같음

        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();
        // attributes == attributes1

        return attributes.toString();
    }

    @GetMapping("/loginInfo")
    @ResponseBody
    public String loginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String result = "";

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        if (principal.getMember().getProvider() == null) {
            result = result + "OAuth2 로그인 : " + principal;
        } else {
            result = result + "Form 로그인 : " + principal;
        }

        return result;
    }

    @GetMapping(value = "/item/detail")
    public String itemDetail() {
        return "pages/item/user/itemDetailForm";
    }

//    @GetMapping(value = "/error")
//    public String error() {
//        return "pages/main/error";
//    }


}