package com.zhj6422.dsdemo.dao;

import com.zhj6422.dsdemo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrdersByUserId(long userId);
    int insertOrder(Order order);
}
