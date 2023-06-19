package com.project.sul.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderPageItemDto {

    /* 아이템 상세 페이지에서 전달 받는 것들 */
    private Long itemId;
    private int itemCount;

    /* DB에서 전달 받는 것들 */
    private String itemNm;
    private int price;
    private double discount;

    /* 전달 할 것들 */
    private int salePrice;
    private int finalPrice;
    private int getPoint;
    private int totalPoint;

    public void saleDetails() {
        this.salePrice = (int) (this.price * (1 - this.discount));
        this.finalPrice = this.salePrice * this.itemCount;
        this.getPoint = (int) (Math.floor((this.salePrice * 0.05)));
        this.totalPoint = this.getPoint * this.itemCount;
    }

}
