package com.example.practice.product.domain.support;

import com.example.practice.category.domain.Category;
import com.example.practice.product.domain.Money;
import com.example.practice.product.domain.Product;
import com.example.practice.member.domain.Member;
import com.example.practice.purchase.domain.Address;
import io.github.benas.randombeans.EnhancedRandomBuilder;

import java.math.BigInteger;

public class ProductDomainBuilder {


    public static Address provideRandomAddress(){
      return  EnhancedRandomBuilder.aNewEnhancedRandom()
                .nextObject(Address.class);
    }

    public static Product provideProductBread(String breadName, String image, int i) {
        return Product.builder()
                .productId(1L)
                .seller(Member.builder().memberId("memberId1").build())
                .price(new Money(BigInteger.valueOf(1000+i)))
                .category(Category.builder().categoryId(9L).build())
                .productName(breadName)
                .description("맛있다")
                .image(image)
                .build();
    }
    public static Product provideProductBread() {
        return Product.builder()
                .productId(1L)
                .seller(Member.builder().memberId("memberId1").build())
                .price(new Money(BigInteger.valueOf(1000)))
                .category(Category.builder().categoryId(8L).build())
                .productName("바게트")
                .description("맛있다")
                .image("D:/web")
                .build();
    }

    public static Product provideHomeAppliance(String name, String image, int i) {
        return Product.builder()
                .productId(1L)
                .seller(Member.builder().memberId("memberId1").build())
                .price(new Money(BigInteger.valueOf(1000 + i)))
                .category(Category.builder().categoryId(10L).build())
                .productName(name)
                .description("냉장냉장")
                .image(image)
                .build();
    }

    public static Product provideCoffee(String name) {
        return Product.builder()
                .productId(1L)
                .seller(Member.builder().memberId("memberId1").build())
                .price(new Money(BigInteger.valueOf(1000)))
                .category(Category.builder().categoryId(8L).build())
                .productName(name)
                .description(name)
                .image("D:/web")
                .build();
    }
    public static Product provideTravel(String name) {
        return Product.builder()
                .productId(1L)
                .seller(Member.builder().memberId("memberId1").build())
                .price(new Money(BigInteger.valueOf(1000)))
                .category(Category.builder().categoryId(11L).build())
                .productName(name)
                .description(name)
                .image("D:/web")
                .build();
    }
}
