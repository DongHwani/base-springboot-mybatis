package com.example.practice.product.application;

import com.example.practice.product.domain.Product;
import com.example.practice.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(Product product){
        productRepository.save(product);
    }
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return productRepository.findProductsByCategoryId(categoryId);
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException());
    }

    public List<Product> findProductsByKeyword(String keyword) {
        return productRepository.findProductsByKeyword(keyword);
    }
}
