package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@Rollback(value = true)
public class OrderRepositoryTest extends OrderBuilder {


    @Test
    public void save() {
        //Given
        Order order = Order.builder()
                .buyer(Member.builder().memberSequence(1L).build())
                .price(BigInteger.valueOf(1000))
                .product(Product.builder().productId(10L).build())
                .purchaseAddress(Address.builder().address("서울")
                        .detailAddress("동작구")
                        .zipCode("101").build())
                .build();

        //When
        orderRepository.save(order);

        //Then
        assertThat(order.getPurchaseId()).isNotNull();
    }

    @Test
    @Rollback(value = false)
    public void findById() {
        //Given
        Order order = savePurchase();

        //When
        Order savedOrder = orderRepository.findById(order.getPurchaseId());

        //Then
        assertThat(order).isEqualTo(savedOrder);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void findAll(int expected) {
        //Given & When
        IntStream.rangeClosed(1, expected).forEach(i -> savePurchase());
        Long memberSequence = 2L;

        List<Order> orders = orderRepository.findAll(memberSequence);

        //Then
        assertThat(orders).hasSize(expected);
    }
}
