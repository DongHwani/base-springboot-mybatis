package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.product.domain.Money;
import lombok.*;

import java.util.List;

@Builder @EqualsAndHashCode(exclude = { "buyer", "totalPrice", "orderLines" })
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private Long orderId;
    private Member buyer;
    private Money totalPrice;
    private List<OrderLine> orderLines;

    public int countOrderLines() {
        return orderLines.size();
    }
}
