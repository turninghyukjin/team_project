package com.project.sul.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Reviews")
public class Review { // 보여지는 리뷰창
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private String nickname;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "item_name")
//    private String itemNm;

    private int starRating; // 별점
    private LocalDateTime regTime; // 상품평 등록시간

    @Lob @Column(nullable = false)
    private String Rating; // 상품 상세후기
}
