package com.project.sul.controller;

import com.project.sul.constant.Role;
import com.project.sul.dto.RegisterSocialFormDto;
import com.project.sul.entity.Address;
import com.project.sul.entity.Member;
import com.project.sul.repository.MemberRepository;
import com.project.sul.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/register")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/social")
    public String registerSocial(Model model) {
        model.addAttribute("registerSocialFormDto", new RegisterSocialFormDto());
        return "pages/main/register_social";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "pages/main/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "pages/main/login";
    }

    @GetMapping(value = "/updateMember")
    public String updateMember(Model model) {
        model.addAttribute("registerSocialFormDto", new RegisterSocialFormDto());
        return "pages/main/update_social";
    }


    // 닉네임 중복 체크 sout 는 확인용
    @PostMapping(value = "/social/nicknameDuplicate")
    @ResponseBody
    public String checkNickname(@RequestParam("nickname") String nickname) {
//        System.out.println(" 컨트롤러 : 넘어온 닉네임 = " + nickname);

        String result = "N"; // 닉네임이 없을 시 (default) N

        Boolean exist = memberService.checkNicknameDuplicate(nickname);
//        System.out.println("컨트롤러 : boolean 값 = " + exist);

        if (exist) result = "Y"; // 닉네임이 있을 시 Y
//        System.out.println("컨트롤러 : YN = " + result);

        return result;
    }

    @PostMapping(value = "/social")
    public String checkResister(@Valid RegisterSocialFormDto registerSocialFormDto,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "pages/main/register_social";
        }

        try {
            Member member = Member.createMember(registerSocialFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pages/main/register_social";
        }

        return "redirect:/";
    }

    //유효성 검사 수정 해야함
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }


}
