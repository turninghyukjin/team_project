package com.project.sul.repository;

import com.project.sul.dto.ItemSearchDto;
import com.project.sul.dto.ReviewDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByItemItemNm(String itemNm);
    @Query("SELECT COUNT(r) FROM Review r WHERE r.item.itemNm = :itemNm")
    Long countByItemNm(@Param("itemNm") String itemNm);
}
