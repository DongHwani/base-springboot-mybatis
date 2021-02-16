package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.product.domain.Product;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderBuilder {

    @Autowired
    protected OrderRepository orderRepository;


    public Order savePurchase(){
        Order order = Order.builder()
                .buyer(Member.builder().memberSequence(2L).build())
                .price(BigInteger.valueOf(1000))
                .product(Product.builder().productId(180L).build())
                .purchaseAddress(Address.builder().address("서울")
                        .detailAddress("동작구")
                        .zipCode("101").build())
                .build();

        orderRepository.save(order);
        return order;
    }

}
