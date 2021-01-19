package com.example.practice.product.domain.support;

import com.example.practice.category.Category;
import com.example.practice.product.domain.Money;
import com.example.practice.product.domain.Product;
import com.example.practice.user.User;

public class ProductDomainBuilder {

    public static Product provideProduct() {
        return Product.builder()
                .productId(1L)
                .writer(User.builder().userId("UserId").build())
                .money(new Money(1000))
                .category(Category.builder().categoryId(1L).build())
                .productName("제목")
                .description("내용본문입니다")
                .image("D:/web")
                .build();
    }
}
