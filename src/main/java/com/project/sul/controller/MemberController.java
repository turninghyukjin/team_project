package com.project.sul.controller;

import com.project.sul.dto.MemberFormDto;
import com.project.sul.dto.RegisterSocialFormDto;
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

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register/final")
    public String registerFinal(
            @Valid MemberFormDto memberFormDto,
            BindingResult bindingResult,
            Model model,
            @RequestParam(required = false) String kakaoEmail
    ) {
        if (bindingResult.hasErrors()) {
            return "pages/main/register_final";
        }

        try {
            if (kakaoEmail != null) {
                // 카카오에서 받은 이메일 값을 사용하여 처리하는 로직
                // 이메일 값을 직접 할당하는 대신 memberFormDto에 설정해줍니다.
                memberFormDto.setEmail(kakaoEmail);
            } else {
                // 일반적인 회원가입 로직
                LocalDate birthDate = memberFormDto.getBirthDate();
                if (!isAgeValid(birthDate)) {
                    model.addAttribute("errorMessage", "만 19세 이상만 가입할 수 있습니다.");
                    return "pages/main/register";
                }

                String impUid = "인증토큰"; // 인증 토큰을 얻어오는 로직이 필요합니다.

                Member member = Member.createMember(memberFormDto, passwordEncoder, memberFormDto.getEmail(), impUid, birthDate);

                Address address = new Address(memberFormDto.getAddress().getZipCode(), memberFormDto.getAddress().getStreetAdr(), memberFormDto.getAddress().getDetailAdr());
                member.setAddress(address);
                member.setPhone(memberFormDto.getPhone());

                memberService.saveMember(member);
            }
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pages/main/register_final";
        }

        model.addAttribute("kakaoEmail", kakaoEmail);
        return "pages/main/register_final";
    }

    private boolean isAgeValid(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        int age = Period.between(birthDate, now).getYears();
        return age >= 19;
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
