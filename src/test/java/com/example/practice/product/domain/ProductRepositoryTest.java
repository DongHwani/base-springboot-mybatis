package com.example.practice.product.domain;

import com.example.practice.category.domain.Category;
import com.example.practice.member.domain.Member;
import com.example.practice.member.domain.MemberRepository;
import com.example.practice.product.domain.support.ProductDomainBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductRepositoryTest extends ProductDomainBuilder {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member seller;

    @BeforeEach
    public void setUpMember(){
        seller = Member.builder()
                .memberId("blue")
                .password("pass")
                .address(provideRandomAddress())
                .phoneNumber("010")
                .build();
        memberRepository.save(seller);
    }

    @AfterEach
    public void setUpClear(){
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 60000000})
    public void save(int money) {
        //Given
        Product product = Product.builder()
                .productId(1L)
                .seller(seller)
                .price(new Money(BigInteger.valueOf(money)))
                .category(Category.builder().categoryId(8L).build())
                .productName("바게트")
                .description("맛있다")
                .image("D:/web")
                .build();

        //When
        productRepository.save(product);
        Product savedProduct = productRepository.findById(product.getProductId()).get();

        //Then
        assertThat(product).isEqualTo(savedProduct);
    }

    @Test
    public void findById(){
        //Given
        Product product = Product.builder()
                .productId(1L)
                .seller(seller)
                .price(new Money(BigInteger.valueOf(5000)))
                .category(Category.builder().categoryId(8L).build())
                .productName("바게트")
                .description("맛있다")
                .image("D:/web")
                .build();
        productRepository.save(product);

        //When
        Product findProduct = productRepository.findById(product.getProductId()).get();

        assertThat(product).isEqualTo(findProduct);
    }


    @Test
    public void findProductsByKeyword(){
        String keyword = "바게트";
        List<Product> products = productRepository.findProductsByKeyword(keyword);

        assertAll(
                () -> assertThat(products).isNotNull()
        );
    }
    @Test
    public void saveEvent(){
        Product event1 = Product.builder()
                .category(Category.builder().categoryId(8L).build())
                .image("assets/event/event_1.jpg")
                .description("스타벅스 할인")
                .price(new Money(BigInteger.valueOf(3000)))
                .productName("스타벅스")
                .seller(Member.builder().memberId("user").build())
                .build();

        productRepository.save(event1);
    }

}
