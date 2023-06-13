package com.project.sul.controller;

import com.project.sul.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MainController {

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

    @GetMapping(value = "/register/agreement")
    public String   agreement() {
        return "pages/main/agreement";
    }

    @GetMapping(value = "/register/email")
    public String registerEmail() {
        return "pages/main/register_email";
    }

    @GetMapping(value = "/register/final")
    public String registerFinal(Model model) {
        model.addAttribute("registerSocialFormDto", new RegisterSocialFormDto());
        return "pages/main/register_social";
    }

    @GetMapping(value = "/item/detail")
    public String itemDetail() {
        return "pages/item/user/itemDetailForm";
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