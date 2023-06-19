package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.entity.Item;
import com.project.sul.entity.ItemDetails;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {
    private Long id;

    @NotBlank
    private String itemNm;

    @NotNull
    private String price; // 가격을 문자열로 저장

    @NotNull
    private Integer stockNumber;

    @NotBlank(message = "상품 상세정보는 필수 입력값입니다")
    private String itemDetail;

    private List<Long> itemImgIds = new ArrayList<>();
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    private ItemSellStatus itemSellStatus;

    @Column(nullable = false)
    private String type; // 상품타입(탁주, 약주, 증류주, 과실주)
    private int abv; // 알콜도수
    private int sweetness; // 단맛
    private int sourness; // 신맛
    private int sparkling; // 탄산

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }

    // 가격 설정 메서드

}
