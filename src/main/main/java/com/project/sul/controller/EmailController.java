package com.project.sul.controller;

import com.project.sul.dto.EmailAuthRequestDto;
import com.project.sul.service.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class EmailController {

    private final EmailServiceImpl emailService;

    @PostMapping("login/mailConfirm")
    public String mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws Exception {

        String authCode = emailService.sendSimpleMessage(emailDto.getEmail());
        return authCode;
    }





}
