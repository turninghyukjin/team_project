package com.project.sul.controller;

import com.project.sul.dto.ItemDto;
import com.project.sul.dto.ItemFormDto;
import com.project.sul.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/admin/item/new")
    public String showItemForm(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }
    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

        if(bindingResult.hasErrors()){ // itemFormDto 에러
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemImgFileList.get(1).isEmpty() && itemFormDto.getId()==null){
            model.addAttribute("errorMessage", "상품 이미지는 필수 입력값입니다.");
            return "item/itemForm";
        }
        try { itemService.saveItem(itemFormDto, itemImgFileList); }
        catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }
//    @GetMapping(value = "/item/{itemId}")
//    public String itemDetail(Model model, @PathVariable("itemId") Long itemId){
//        ItemFormDto itemFormDto = itemService.getItem(itemId);
//        model.addAttribute("item", itemFormDto);
//        return "item/itemDetail";
//    }
}

