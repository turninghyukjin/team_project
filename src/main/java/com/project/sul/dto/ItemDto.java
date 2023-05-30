package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDto { // 기본 틀 (여긴 생각 해볼게요)
    private Long id;
    private String itemNm;          // 상품명
    private String itemStory;       // 상세설명
    private int price;              // 가격

    private ItemSellStatus itemSellStatCd; // 상태
}
