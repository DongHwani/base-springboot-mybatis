package com.example.practice.order.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderRepository {

    void save(Order purchase);
    //구매아이디기준으로 구매이력을 가져옴
    Order findById(Long purchaseId);
    //회원시퀀스기준으로 모든 구매이력을 가져옴
    List<Order> findAll(Long memberSequence);

}
