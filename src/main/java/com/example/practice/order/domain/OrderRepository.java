package com.example.practice.order.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderRepository {

    void save(Order purchase);

    Order findById(Long purchaseId);

    List<Order> findAll(Long memberSequence);

}
