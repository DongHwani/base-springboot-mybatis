package com.example.practice.product.application;

import com.example.practice.product.domain.Product;
import com.example.practice.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public void saveProduct(Product product){
        logger.info("{}", product);
        productRepository.saveProduct(product);
    }

}
