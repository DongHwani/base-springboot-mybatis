package com.example.practice.order.application;

import com.example.practice.cart.domain.Cart;
import com.example.practice.cart.domain.CartRepository;
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
    private CartRepository cartRepository;

    //구매이력을 저장한다. 기존에 장바구니에 구매한 상품이 있다면 장바구니에 있는 내역은 삭제한다
    public Order save(Order order){
        Cart cart = cartRepository.findByProductId(order.getPurchaseId());
        if(cart != null){
            cartRepository.deleteByProductId(order.getPurchaseId());
        }
        orderRepository.save(order);
        return order;
    }
}
