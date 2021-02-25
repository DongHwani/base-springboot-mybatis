package com.example.practice.order.domain;

import com.example.practice.member.domain.Member;
import com.example.practice.product.domain.Money;
import com.example.practice.product.domain.Product;
import com.example.practice.product.domain.support.ProductDomainBuilder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderRepositoryTest  {

    @Autowired
    private OrderRepository orderRepository;

    @ParameterizedTest
    @MethodSource("provideOrderProducts")
    public void save(List<Product> orderProducts) {
        //Given
        BigInteger totalPrice = orderProducts.stream()
                                        .map(p -> p.getMoney())
                                        .reduce(BigInteger.ZERO, BigInteger::add);
        Order order = Order.builder()
                .buyer(Member.builder().memberId("구매자").build())
                .totalPrice(new Money(totalPrice))
                .orderLines(orderProducts)
                .build();

        //When
        orderRepository.save(order);

        //Then
        assertAll(
                () -> assertThat(order.getOrderId()).isNotNull()

        );
    }

    private static Stream<Arguments> provideOrderProducts() {
        return  Stream.of(
                Arguments.of(
                        ProductDomainBuilder.provideProductList(5)
                )
        );
    }

}
