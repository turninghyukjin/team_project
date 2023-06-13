package com.project.sul.entity;

import com.project.sul.dto.ItemImgDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="item_img")
public class ItemImg  {
    @Id
    @Column(name="item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; // 이미지파일명
    private String oriImgName; // 원본파일명
    private String imgUrl; // 이미지 조회경로
    private String repImgYn; // 대표이미지 여부

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

//    메소드
    public void updateItemImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}