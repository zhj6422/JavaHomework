package com.zhj6422.dsdemo.service;

import com.zhj6422.dsdemo.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserIdWithSlave1(long userId);
    List<Order> getOrdersByUserIdWithSlave2(long userId);
    int insertOrder(Order order);
}
