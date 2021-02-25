package com.example.practice.order.domain;

public interface OrderBaseSave {

    Long saveOrder(Order order);
    void saveOrderLines(Order orderLines);

}
