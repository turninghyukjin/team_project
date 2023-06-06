package com.project.sul.service;


import com.project.sul.dto.OrderDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.Member;
import com.project.sul.entity.Order;
import com.project.sul.entity.OrderItem;
import com.project.sul.repository.ItemRepository;
import com.project.sul.repository.MemberRepository;
import com.project.sul.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final MemberService memberService;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;


























    @Autowired
    public OrderService(MemberService memberService, ItemRepository itemRepository, MemberRepository memberRepository, OrderRepository orderRepository) {
        this.memberService = memberService;
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
    }

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(ExceptionInInitializerError::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}