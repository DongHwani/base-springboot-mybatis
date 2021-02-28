package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member seller;

    @BeforeEach
    public void sellerSetup(){
        seller = memberRepository.findByMemberId("blue").get();
    }

    @Test
    public void save() {
        //Given
        Order order = OrderBuilder.provideOrder(5, seller);

        //When
        orderRepository.save(order);

        //Then
        assertAll(
                () -> assertThat(order.getOrderId()).isNotNull(),
                () -> assertThat(order.getOrderLines().size()).isGreaterThan(0)
        );
    }

    @Test
    public void findByOrderWithOrderLinesTest() {
        //Given
        Order order = OrderBuilder.provideOrder(5, seller);
        orderRepository.save(order);

        //When
        Order savedOrder = orderRepository.findByIdWithOrderLines(order.getOrderId());

        //Then
        assertAll(
                () -> assertThat(order).isEqualTo(savedOrder),
                () -> assertThat(order.countOrderLines()).isEqualTo(savedOrder.countOrderLines())
        );
    }



}
