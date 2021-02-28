package com.example.practice.order.domain;

import com.example.practice.product.domain.Product;
import lombok.*;

@Builder @EqualsAndHashCode(exclude = { "product" })
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

    private Long orderLineId;
    private Product product;

}
