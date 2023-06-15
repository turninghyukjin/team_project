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
        return "pages/main/register_final";
    }

    @GetMapping(value = "/item/detail")
    public String itemDetail() {
        return "pages/item/user/itemDetailForm";
    }



}