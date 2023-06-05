package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto { // 상품등록
    private Long id;

    @NotBlank(message="상품명은 필수 입력값입니다")
    private String itemNm;

    @NotBlank(message="상품 상세는 필수 입력값입니다")
    private String itemDetail;

    @NotNull(message="가격은 필수 입력값입니다")
    private int price; // 가격

    @NotNull(message="재고는 필수 입력값입니다")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;


    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();


    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }
    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }

}
