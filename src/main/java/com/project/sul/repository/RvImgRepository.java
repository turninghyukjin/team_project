package com.project.sul.repository;

import com.project.sul.entity.ItemImg;
import com.project.sul.entity.ReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RvImgRepository extends JpaRepository<ReviewImg, Long> {
}
