package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ItemSearchDto { // 검색할 것들
    private String type;
    private int abv;
    private int sweetness;
    private int sourness;
    private int sparkling;
    private String itemNm;
    private ItemSellStatus itemSellStatus;
    private int stockNumber;
    private String searchQuery= "";
}

