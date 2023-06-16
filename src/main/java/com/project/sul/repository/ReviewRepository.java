package com.project.sul.repository;

import com.project.sul.entity.Item;
import com.project.sul.entity.Member;
import com.project.sul.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByItemItemId(Long id);
    // 갯수
    @Query("SELECT COUNT(r) FROM Review r WHERE r.item.id = :id")
    Long countByItemId(@Param("itemId") Long itemId);

    List<Review> findAllByItemAndMember(Long itemId, Long memberId);
}
