package com.example.practice.order.application;

import com.example.practice.order.domain.Order;
import com.example.practice.order.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){
        orderRepository.save(order);
        return order;
    }
}
