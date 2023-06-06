package com.project.sul.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "KCP",
        sequenceName = "KCP_INFO_SEQ",
        initialValue = 1,
        allocationSize = 1)
public class PaymentsInfo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "KCP_INFO_SEQ_GENERATOR"
    )
    private long kcpmentNo;

    @Column(nullable = false, length = 100)
    private String kcpMethod;

    @Column(nullable = false, length = 100)
    private String impUid;

    @Column(nullable = false, length = 100)
    private String merchantUid;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false, length = 100)
    private String buyerAddr;

    @Column(nullable = false, length = 100)
    private String buyerPostcode;

    @OneToOne(mappedBy = "paymentsInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderItem orderItem;

    // 추가 필드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private Member member;
}

