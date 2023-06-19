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

    @NotBlank(message = "상품명은 필수 입력값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력값입니다.")
    private int price;

    @NotNull(message = "재고 수량은 필수 입력값입니다.")
    private Integer stockNumber;

    @NotBlank(message = "상품 상세정보는 필수 입력값입니다")
    private String itemDetail;

    private List<Long> itemImgIds = new ArrayList<>();
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    @NotBlank(message = "판매 상태는 필수 입력값입니다.")
    private ItemSellStatus itemSellStatus;

    @NotBlank(message = "술 종류는 필수 입력값입니다.")
    @Column(nullable = false)
    private String type; // 상품타입(탁주, 약주, 증류주, 과실주)
    
    @NotBlank(message = "술 도수는 필수 입력값입니다.")
    private int abv; // 알콜도수
    @NotBlank(message = "술 단맛 정도는 필수 입력값입니다.")
    private int sweetness; // 단맛
    @NotBlank(message = "술 신맛 정도는 필수 입력값입니다.")
    private int sourness; // 신맛
    @NotBlank(message = "술 탄산 정도는 필수 입력값입니다.")
    private int sparkling; // 탄산

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }



}
