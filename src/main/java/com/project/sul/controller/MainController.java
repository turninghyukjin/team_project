package com.project.sul.controller;

import com.project.sul.dto.MemberFormDto;
import com.project.sul.dto.RegisterSocialFormDto;
import com.project.sul.entity.Address;
import com.project.sul.entity.Member;
import com.project.sul.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/")
    public String main() {
        return "pages/main/main";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "pages/main/login";
    }

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