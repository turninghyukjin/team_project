package com.project.sul.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto { // 주문자 상세페이지로 넘어갈 dto

    private Long id;
    private String itemNm;          // 상품명
    private String itemDetail;      // 상세설명
    private String type;            // 타입
    private float abv;              // 도수
    private float starRating;       // 평가
    private int price;              // 가격
}
