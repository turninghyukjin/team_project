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
public class Item extends BaseEntity { // 관리자 상품등록
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

    private int abv; // 알콜도수
    private int sweetness; // 단맛
    private int sourness; // 신맛
    private int sparkling; // 탄산

    @Lob
    @Column(nullable = false)
    private String itemDetail; // #상품상세설명

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="itemDetail_id") // 단방향 상세정보(도수, 맛)
//    private ItemDetails itemDetails;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;      //등록 시간 >> 유통기한과 연관?
    private LocalDateTime updateTime;   //수정 시간

    private String taste; // 핵심포인트-맛
    private String sideDish; // 핵심포인트-안주
    private String ingredient; // 핵심포인트-재료

//    private int expire; // 유통기한
//    private String conserve; // 보관방법

    private double avgStar; // 평균별점
    private int numComment;  // 리뷰개수

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
