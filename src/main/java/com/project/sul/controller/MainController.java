package com.project.sul.controller;

import com.project.sul.dto.MemberFormDto;
import com.project.sul.dto.RegisterSocialFormDto;
import com.project.sul.entity.Address;
import com.project.sul.entity.Member;
import com.project.sul.kako.KakaoService;
import com.project.sul.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
//@RequiredArgsConstructor
public class MainController {

//    private final MemberService memberService;
//    private final PasswordEncoder passwordEncoder;
//    private final Member member;

    @GetMapping(value = "/")
    public String main() {
        return "pages/main/main";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "pages/main/login";
    }



//    @RequestMapping("/kakao-home")
//    public String kakao(@RequestParam(value = "code", required = false) String code) throws Exception{
//        System.out.println("#########" + code);
//        return "pages/main/login";
//    }
//    @Autowired
//    private KakaoService kakaoService;
//
//    @RequestMapping("/kakao-login")
//    public String kakaohome(@RequestParam(value = "code", required = false) String code) throws Exception{
//        System.out.println("#########" + code);
//        String access_Token = kakaoService.getAccessToken(code);
//        System.out.println("###access_Token#### : " + access_Token);
//        return "pages/main/login";
//    }



    @GetMapping(value = "/register")
    public String register() {
        return "pages/main/register";
    }

    @GetMapping(value = "/register/email")
    public String registerEmail() {
        return "pages/main/register_email";
    }

    @GetMapping(value = "/register/social")
    public String registerSocial(Model model) {
        model.addAttribute("registerSocialFormDto", new RegisterSocialFormDto());
        return "pages/main/register_social";
    }
// 닉네임 중복 체크
//    @GetMapping(value = "/register/social/{nickname}/duplicate")
//    public ResponseEntity<Boolean> checkNicknameDuplicate (@PathVariable String nickname) {
//        return ResponseEntity.ok(memberService.saveMember(member));
//    }

//유효성 검사 수정 해야함
//    public Map<String, String> validateHandling(Errors errors) {
//        Map<String, String> validatorResult = new HashMap<>();
//
//        for (FieldError error : errors.getFieldErrors()) {
//            String validKeyName = String.format("valid_%s", error.getField());
//            validatorResult.put(validKeyName, error.getDefaultMessage());
//        }
//        return validatorResult;
//    }


//    @PostMapping(value = "/register/social")
//    public String registerSocialMember(@Valid  memberFormDto,
//                                    BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "pages/main/register_social";
//        }
//        try {
//            Address address = new Address()
//            Member member = Member.createMember(memberFormDto, passwordEncoder);
//            memberService.saveMember(member);
//        } catch (IllegalStateException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "pages/main/register_social";
//        }
//        return "redirect:/";
//    }

}