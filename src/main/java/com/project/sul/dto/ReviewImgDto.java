package com.project.sul.dto;

import com.project.sul.entity.ReviewImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ReviewImgDto {

    private Long id;

    private String reImgName; // 리뷰이미지파일명
    private String reOriImgName; // 리뷰원본이미지 파일명
    private String reImgUrl; // 리뷰이미지 조회경로

    private static ModelMapper modelMapper = new ModelMapper();
    public static ReviewImgDto of(ReviewImg reviewImg){
        return modelMapper.map(reviewImg, ReviewImgDto.class);
    }
}


