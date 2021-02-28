package com.example.practice.order.domain;

import java.util.List;

public class OrderLines {

    private List<OrderLine> orderLines;

    public OrderLines(List<OrderLine> orderLines){
        if(orderLines.size() <= 0) {
            throw new IllegalArgumentException();
        }
        this.orderLines = orderLines;
    }

    public int size() {
        return orderLines.size();
    }
}
