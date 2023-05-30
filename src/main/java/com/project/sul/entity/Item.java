package com.project.sul.entity;

import com.project.sul.constant.ItemSellStatus;
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
public class Item {

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

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품상세설명

    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime; //등록 시간

    private LocalDateTime updateTime; //수정 시간

}
