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

    private Item item;

    private Long id;

    @NotBlank
    private String itemNm;

    private Integer price;

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
        if (item == null) {
            item = new Item();
        }
        item.setPrice(price); // 아이템 생성 시에도 가격 설정
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        ItemFormDto itemFormDto = modelMapper.map(item, ItemFormDto.class);
        itemFormDto.setPrice(item.getPrice()); // 가져온 아이템의 가격 설정
        return itemFormDto;
    }
}
