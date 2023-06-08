package com.project.sul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}