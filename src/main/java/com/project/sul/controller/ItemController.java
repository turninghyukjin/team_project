package com.project.sul.controller;

import com.project.sul.dto.ItemFormDto;
import com.project.sul.dto.ItemSearchDto;
import com.project.sul.entity.Item;
import com.project.sul.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 새로운 아이템 등록
    @GetMapping(value = "/admin/item/register")
    public String itemRegister(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "pages/item/admin/itemRegisterForm";
    }
    // 업데이트
    @GetMapping(value = "/admin/item/update")
    public String itemUpdate(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "pages/item/admin/itemUpdateForm";
    }

    @PostMapping("/admin/item/newValid")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

        if (bindingResult.hasErrors()) { // itemFormDto 에러
            return "pages/item/admin/itemRegisterForm";
        }
        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "상품 이미지는 필수 입력값입니다.");
            return "pages/item/admin/itemRegisterForm";
        }

        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
            return "pages/item/admin/itemRegisterForm";
        }
        return "redirect:/";
    }

    // 수정목록 조회
    @GetMapping("/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model) {
        try {
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
            System.out.println(itemId);
            System.out.println("나의 이름은" + itemFormDto.getItemNm());
            System.out.println("이미지" + itemFormDto.getItemImgIds()); // 안나옴
            System.out.println("이미지" + itemFormDto.getItemImgDtoList());
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "pages/item/admin/itemRegisterForm"; // 해당 상품이 없을 땐 등록페이지로
        }
        return "pages/item/admin/itemUpdateForm";
    }

    // 수정
    @PostMapping("/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {
        if (bindingResult.hasErrors()) {
            return "pages/item/admin/itemUpdateForm";
        }
        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력값입니다");
            return "pages/item/admin/itemUpdateForm";
        }
        try {
            itemService.updateItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생했습니다");
            return "pages/item/admin/itemRegisterForm";
        }
        return "redirect:/";
    }


    // 관리자조회
    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page")
    Optional<Integer> page, Model model) {

        // 페이지번호가 있으면 get, 없으면 0,   + 한페이지당 게시물은 100개 할당(페이지의 크기)
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "item/admin/itemMng";
    }

    // 아이템 상세페이지
    @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId) {
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDto);
        return "item/user/itemDetail";
    }
}

