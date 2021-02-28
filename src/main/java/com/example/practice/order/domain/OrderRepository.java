package com.example.practice.order.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderRepository extends OrderBaseSave {

    default void save(Order order) {
        saveOrder(order);
        saveOrderLines(order);
    }

    Order findByIdWithOrderLines(Long orderId);

}
