package com.project.sul.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Embeddable
public class ItemDetails {
    // https://higher77.tistory.com/86

    @Id
    @Column(name="itemDetail_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int r_abv; // 알콜도수
    private int r_sweet; // 단맛
    private int r_sour; // 신맛
    private int r_sparkle; // 탄산
}
