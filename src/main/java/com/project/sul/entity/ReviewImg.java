package com.project.sul.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "reviewImg")
public class ReviewImg extends BaseEntity {
    @Id
    @Column(name="review_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String rvImgName;    // 이미지파일명
    private String rvOriImgName; // 원본파일명
    private String rvImgUrl;     // 이미지 조회경로

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    // 이미지정보 업데이트 메소드
    public void updateReImg(String rvImgName, String rvOriImgName, String rvImgUrl) {
        this.rvImgName = rvImgName;
        this.rvOriImgName = rvOriImgName;
        this.rvImgUrl = rvImgUrl;
    }
}
