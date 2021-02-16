package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.product.domain.Product;
import lombok.*;

import java.math.BigInteger;

@Builder @EqualsAndHashCode(exclude = {"product", "buyer", "price", "purchaseAddress"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private Long purchaseId;
    private Product product;
    private Member buyer;
    private BigInteger price;
    private Address purchaseAddress;

}
