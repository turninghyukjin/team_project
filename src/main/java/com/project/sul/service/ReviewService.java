package com.project.sul.service;

import com.project.sul.dto.ReviewFormDto;
import com.project.sul.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Long saveReview(ReviewFormDto reviewFormDto, List<MultipartFile> reviewImgFileList) throws Exception{
        return  null;
    }
//    public int countReviews(Long itemId) {
//        return reviewRepository.countReviews(itemId);
//    }

}
