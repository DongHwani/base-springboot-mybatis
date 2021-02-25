package com.example.practice.order.domain;

import com.example.practice.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderRepository extends OrderBaseSave {

    default void save(Order order) {
        saveOrder(order);
        saveOrderLines(order);
    }

    List<Product> findOrderHistoryByOrderId(Long orderId);

}
