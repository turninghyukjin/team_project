package com.project.sul.repository;

import com.project.sul.entity.ItemImg;
import com.project.sul.entity.ReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RvImgRepository extends JpaRepository<ReviewImg, Long> {
    List<ReviewImg> findByReviewId(Long reviewId);
}
