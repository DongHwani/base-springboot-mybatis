package com.example.practice.product.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface ProductRepository {

    Long save(Product product);

    Optional<Product> findById(final Long productId);

    List<Product> findProductsByCategoryId( final Long categoryId);

    List<Product> findProductsByKeyword(String keyword);

}
