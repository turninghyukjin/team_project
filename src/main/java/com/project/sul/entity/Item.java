package com.project.sul.entity;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.dto.ItemFormDto;
import com.project.sul.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item { // 관리자

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품 코드

    @Column(nullable = false,length = 100)
    private String itemNm; //상품명

    @Column(name = "price",nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Column(nullable = false)
    private String type; //상품타입(탁주, 약주, 증류주, 과실주)

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품상세설명

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="itemDetail_id") // 단방향 상세정보(도수, 맛)
    private ItemDetails itemDetails;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;      //등록 시간 >> 유통기한과 연관?
    private LocalDateTime updateTime;   //수정 시간


// 메서드
    public void updateItem(ItemFormDto itemFormDto) {
        this.itemNm = itemFormDto.getItemNm(); // 상품명
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

// 재고가 없을 때의 예외
    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 :" + this.stockNumber + ")");
        }
        this.stockNumber= restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber = stockNumber;
    }
}
