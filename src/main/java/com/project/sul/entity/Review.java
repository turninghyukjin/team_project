package com.project.sul.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_name", referencedColumnName = "itemNm")
//    @JoinColumn(name = "item_id", referencedColumnName = "itemId")
    private Item item; // 상품명

    private Double Star; // 별점
    // private LocalDateTime regTime; // 상품평 등록시간

    @Lob @Column(nullable = false)
    private String Comment; // 상품 상세후기

    public Review(long l, Object o, Item item, double v, LocalDateTime now, String comment1) {
    }
}
