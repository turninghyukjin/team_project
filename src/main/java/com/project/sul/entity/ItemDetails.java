package com.project.sul.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Embeddable
public class ItemDetails { // 쿼리용으로 사용할 내용

    // https://higher77.tistory.com/86


    @Id
    @Column(name="itemDetail_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

//    private int abv; // 알콜도수
//    private int sweetness; // 단맛
//    private int sourness; // 신맛
//    private int sparkling; // 탄산

    private int r_abv; // 알콜도수
    private int r_sweetness; // 단맛
    private int r_sourness; // 신맛
    private int r_sparkling; // 탄산
}
