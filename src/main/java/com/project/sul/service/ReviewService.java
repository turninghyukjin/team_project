package com.project.sul.service;

import com.project.sul.dto.ReviewDto;
import com.project.sul.dto.ReviewFormDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.Member;
import com.project.sul.entity.Review;
import com.project.sul.repository.ItemRepository;
import com.project.sul.repository.ReviewRepository;
import com.project.sul.repository.RvImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;
    private final RvImgRepository rvImgRepository;
    private final ReviewImgService reviewImgService;

    // 리뷰수가 0 이상일 때 별점계산
    public Double calAvgStar(Item item) {
        List<Review> reviews = reviewRepository.findByItemItemNm(item.getItemNm());
        Double sum = reviews.stream().mapToDouble(Review::getAvgStar).sum();
        if (reviews.size() > 0) {
            return sum / reviews.size();
        } else { return 0.0; }
    }
    // 호출
    public void callUpdateAvgStar(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            Double avgStar = calAvgStar(item);
            updateAvgStar(id, avgStar);
        }
    }
    // 별점 업데이트
    public void updateAvgStar(Long itemId, Double avgStar) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            item.setAvgStar(avgStar);
            itemRepository.save(item);
        }
    }
//    public void updateAvgStarForItem(Item item) {
//        List<Review> reviews = reviewRepository.findByItemItemNm(item.getItemNm());
//        Double sum = reviews.stream().mapToDouble(Review::getAvgStar).sum();
//        Double avgStar = reviews.size() > 0 ? sum / reviews.size() : 0.0;
//        item.setAvgStar(avgStar);
//        itemRepository.save(item);
//    }

    // 리뷰 메서드
    public void save(ReviewFormDto reviewFormDto, Member member, Item item){
        Review review = reviewFormDto.createReview();
        review.setMember(member);
        review.setItem(item);

        reviewRepository.save(review);
    }

}
