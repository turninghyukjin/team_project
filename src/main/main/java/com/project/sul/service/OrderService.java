package com.project.sul.service;

import com.project.sul.constant.OrderStatus;
import com.project.sul.dto.OrderDto;
import com.project.sul.dto.OrderItemDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.Member;
import com.project.sul.entity.Order;
import com.project.sul.entity.OrderItem;
import com.project.sul.repository.ItemRepository;
import com.project.sul.repository.MemberRepository;
import com.project.sul.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

        // 주문 상태를 취소로 변경
        order.setOrderStatus(OrderStatus.CANCEL);

        // 추가적인 작업 수행 (결제 취소 등)

        // 주문 상태 업데이트
        orderRepository.save(order);
    }

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(ExceptionInInitializerError::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        // 주문 결제 정보 설정
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setPaymentAmount(orderDto.getPaymentAmount());
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);

        return order.getId();
    }

    public List<Order> getOrdersByMemberEmail(String email) {
        return orderRepository.findByMemberEmail(email);
    }

}
