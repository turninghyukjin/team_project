package com.project.sul.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격

    private int count; //수량

    // PaymentsInfo 필드 추가
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payments_info_id")
    private PaymentsInfo paymentsInfo;


    public static OrderItem createOrderItem(Item item,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);


        orderItem.setOrderPrice(item.getPrice());

            item.removeStock(count);
            return  orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }

}
