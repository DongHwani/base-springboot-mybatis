package com.example.practice.product.infrastructure;

import com.example.practice.product.domain.Product;
import com.example.practice.product.domain.ProductRepository;
import com.example.practice.product.domain.Products;
import com.example.practice.product.domain.support.ProductDomainBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductRepositoryTestImpl extends ProductDomainBuilder {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveProductTest(){
        //Given & When
        Long result = productRepository.saveProduct(provideProduct());

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    public void findByIdTest(){
        //Given & When
        Optional<Product> product = productRepository.findById(7L);

        assertThat(product.isPresent()).isTrue();
    }

    @Test
    public void findByAllTest(Products products) {
        //Given
        productRepository.findByAll();
    }




}
