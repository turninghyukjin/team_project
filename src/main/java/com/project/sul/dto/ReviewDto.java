package com.project.sul.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReviewDto { // 실제 아래 보이는 리뷰
    private Long id;
    private String name; // 고객이름
    private String itemNm; // 상품명

    private double avgStar; // 별점
    private LocalDateTime regTime; // 상품평 등록시간
    private String Comment;

    private List<ReviewImgDto> reImgDtos = new ArrayList<>();
//    private ReviewImgDto reviewImgDto;
}
