package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.entity.Address;
import com.project.sul.entity.ItemDetails;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDto { // 기본 틀 (스토어와 연결)
    private Long id;
    private String itemNm;          // 상품명
    private int price;              // 가격
    private double avgStar;           // 평균별점
    private int numComment;          // 리뷰수
    private String itemDetail;       // 상세설명
    private String imgUrl;          // 사진

    private ItemSellStatus itemSellStatCd; // 재고상태

    @QueryProjection
    public ItemDto(Long id, String itemNm, int price, double avgStar, int numComment, String itemDetail, String imgUrl) {
        this.id = id;
        this.itemNm = itemNm;
        this.price = price;
        this.avgStar = avgStar;
        this.numComment = numComment;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
    }
}
