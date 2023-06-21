package com.project.sul.service;

import com.project.sul.dto.*;
import com.project.sul.entity.*;
import com.project.sul.repository.ItemRepository;
import com.project.sul.repository.ReviewRepository;
import com.project.sul.repository.RvImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
        List<Review> reviews = reviewRepository.findByItemId(item.getId());
        Double sum = reviews.stream().mapToDouble(Review::getStar).sum();
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
//    public void save(ReviewFormDto reviewFormDto, Member member, Item item){
//        Review review = reviewFormDto.createReview();
//        review.setMember(member);
//        review.setItem(item);
//        reviewRepository.save(review);
//    }

    // 등록
    public Long saveReview(ReviewFormDto reviewFormDto, List<MultipartFile> reviewImgFileList) throws Exception {
        Review review = reviewFormDto.createReview();
        reviewRepository.save(review);

        for (int i = 0; i < reviewImgFileList.size(); i++) {
            ReviewImg reviewImg = new ReviewImg();
            reviewImg.setReview(review);
            reviewImgService.saveReviewImg(reviewImg, reviewImgFileList.get(i));
        }
        return review.getId();
    }

    // 조회
    @Transactional(readOnly = true)
    public ReviewFormDto getReview(Long reviewId){
        List<ReviewImg> reviewImgList = rvImgRepository.findByReviewId(reviewId);
        List<ReviewImgDto> reviewImgDtoList = new ArrayList<>();

        for(ReviewImg reviewImg : reviewImgList){
            ReviewImgDto reviewImgDto = ReviewImgDto.of(reviewImg);
            reviewImgDtoList.add(reviewImgDto);
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(EntityNotFoundException::new);
        ReviewFormDto reviewFormDto = ReviewFormDto.of(review);
        reviewFormDto.setReivewImgDtoList(reviewImgDtoList);
        return reviewFormDto;
    }

}
