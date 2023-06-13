package com.project.sul.dto;

import com.project.sul.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReviewFormDto { // 고객이 작성하는 리뷰창

    private Long id;
    private String name;    // 고객이름
    private String itemNm;  // 상품명

    @NotBlank(message="필수 입력값입니다")
    private int starRating; // 별점
    @Size(min = 20, message = "상품평은 20자 이상 입력해주세요.")
    private String Rating;  // 상세리뷰

    private List<ReviewImgDto> reivewImgDtoList = new ArrayList<>();
    private List<Long> reviewImgIds = new ArrayList<>(); // 수정시 reviewImgId 저장용

    private static ModelMapper modelMapper = new ModelMapper();

    public Review createItem(){
        return modelMapper.map(this, Review.class);
    }
    public static ReviewFormDto of(Review review){
        return modelMapper.map(review, ReviewFormDto.class);
    }
}
