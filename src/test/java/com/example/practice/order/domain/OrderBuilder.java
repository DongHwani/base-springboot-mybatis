package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.product.domain.Money;
import com.example.practice.product.domain.Product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class OrderBuilder {

    public static Order provideOrder(int size, Member seller){
        Order order = Order.builder()
                .buyer(Member.builder().memberId("red").build())
                .totalPrice(new Money(BigInteger.valueOf(1000)))
                .orderLines(provideOrderLines(size, seller))
                .build();
        return order;
    }

    private static OrderLines provideOrderLines(int size, Member seller) {
        List<OrderLine> orderLines = new ArrayList<>();
        IntStream.range(0, size).forEach(i -> orderLines.add(provideOrderLine(seller)));
        return new OrderLines(orderLines);
    }

    private static OrderLine provideOrderLine(Member seller) {
        return OrderLine.builder()
                .product(Product.builder()
                            .productId(49L)
                            .seller(seller)
                            .productName("선풍기")
                            .price(new Money(BigInteger.valueOf(1200)))
                            .build())
                .build();
    }
}
