package com.project.sul.repository;

import com.project.sul.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByMemberEmail(String email);
}
