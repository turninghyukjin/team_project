package com.project.sul.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {
    private Long id;

    private String imgName; // 이미지파일명
    private String oriImgName; // 원본이미지 파일명
    private String imgUrl; // 이미지 조회경로
    private String repImgYn; // 대표이미지 여부
}
