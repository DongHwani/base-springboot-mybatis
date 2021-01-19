package com.example.practice.product.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface ProductRepository {

    Long saveProduct(Product product);

    Optional<Product> findById(final Long productId);

    Products findByAll();
}
