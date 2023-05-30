package com.project.sul.controller;

import com.project.sul.dto.ItemFormDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ItemController {
    @GetMapping("")
    public String itemFrom(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }
}
