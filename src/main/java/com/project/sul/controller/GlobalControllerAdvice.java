package com.project.sul.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute
    public void addCommonAttributes(Model model, HttpSession session) {
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
    }
}
