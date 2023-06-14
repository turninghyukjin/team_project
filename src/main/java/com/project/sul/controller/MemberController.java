package com.project.sul.controller;

import com.project.sul.dto.MemberFormDto;
import com.project.sul.entity.Member;
import com.project.sul.entity.Address;
import com.project.sul.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;

@RequestMapping("/join")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @GetMapping(value = "/join")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "pages/user/join";
    }

    @PostMapping(value = "/join")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "pages/user/join";
        }

        try {
            LocalDate birthDate = memberFormDto.getBirthDate();
            if (!isAgeValid(birthDate)) {
                model.addAttribute("errorMessage", "만 19세 이상만 가입할 수 있습니다.");
                return "pages/user/join";
            }

            String impUid = "인증토큰"; // 인증 토큰을 얻어오는 로직이 필요합니다.

            Member member = Member.createMember(memberFormDto, passwordEncoder, memberFormDto.getEmail(), impUid, birthDate);

            Address address = new Address(memberFormDto.getAddress().getZipCode(), memberFormDto.getAddress().getStreetAdr(), memberFormDto.getAddress().getDetailAdr());
            member.setAddress(address);
            member.setPhone(memberFormDto.getPhone());

            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pages/user/join";
        }
        return "redirect:/";
    }

    // 로그인
    @GetMapping(value = "/login")
    public String loginMember() {
        return "/user/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/user/login";
    }

    private boolean isAgeValid(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        int age = Period.between(birthDate, now).getYears();
        return age >= 19;
    }
}
