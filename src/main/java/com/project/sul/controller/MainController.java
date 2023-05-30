package com.project.sul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String main() {
        return "pages/main/main";
    }

    @GetMapping(value = "/register")
    public String memberSignUp() {
        return "pages/member/signUp";
    }

}