package com.project.sul.service;

import com.project.sul.entity.Item;
import com.project.sul.entity.Review;
import com.project.sul.repository.ItemRepository;
import com.project.sul.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;

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
}
