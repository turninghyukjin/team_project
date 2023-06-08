package com.project.sul.dto;

import com.project.sul.constant.OrderStatus;
import com.project.sul.entity.Member;
import com.project.sul.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private double totalPrice;
    private List<OrderItemDto> orderItems;

    @NotNull(message = "상품 아이디를 입력해주세요!")
    private Long itemId;

    @Min(value = 1, message = "최소 주문 수량은 1개입니다.")
    @Max(value = 999, message = "최대 주문 수량은 999입니다.")
    private int count;

    // Getter and Setter methods

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private String paymentMethod;
    private double paymentAmount;

    // getter 메서드
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }


}
