package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.product.domain.Money;
import lombok.*;

@Builder @EqualsAndHashCode(exclude = { "buyer", "orderLines" })
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private Long orderId;
    private Member buyer;
    private Money totalPrice;
    private OrderLines orderLines;

}
