package com.project.sul.controller;

import com.project.sul.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders/member")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/members/{email}")
    public List<com.project.sul.entity.Order> getOrdersByMemberEmail(@PathVariable String email) {
        return orderService.getOrdersByMemberEmail(email);
    }
}
