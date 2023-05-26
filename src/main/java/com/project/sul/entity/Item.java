package com.project.sul.entity;

import com.project.sul.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Getter
@Setter
@ToString
public class Item {
    @Id @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemNm; // 상품명
    private ItemDetail itemDetail; // 상세사항 >> 단방향
    private int price; // 가격

    @Lob
    @Column(nullable = false)
    private String itemStory; // 상세설명

    private int stockNumber; // 재고
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;


}
