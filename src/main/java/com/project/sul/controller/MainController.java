package com.project.sul.controller;

import com.project.sul.dto.*;
import com.project.sul.entity.Address;
import com.project.sul.entity.Member;
import com.project.sul.service.ItemService;
import com.project.sul.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
//@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

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

    @GetMapping(value = "/register/social")
    public String registerSocial(Model model) {
        model.addAttribute("itemFormFto", new ItemFormDto());
        return "pages/main/register_social";
    }

    @GetMapping(value = "/admin/item/register")
    public String itemRegister(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "pages/item/admin/itemRegisterForm";
    }

    @GetMapping(value = "/admin/item/update")
    public String itemUpdate(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "pages/item/admin/itemUpdateForm";
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