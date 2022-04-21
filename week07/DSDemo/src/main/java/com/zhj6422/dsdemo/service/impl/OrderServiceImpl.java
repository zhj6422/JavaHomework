package com.zhj6422.dsdemo.service.impl;

import com.zhj6422.dsdemo.annotation.MyDataSource;
import com.zhj6422.dsdemo.annotation.Read1;
import com.zhj6422.dsdemo.annotation.Read2;
import com.zhj6422.dsdemo.annotation.Write;
import com.zhj6422.dsdemo.config.DataSourceEnum;
import com.zhj6422.dsdemo.dao.OrderMapper;
import com.zhj6422.dsdemo.entity.Order;
import com.zhj6422.dsdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    @Read1
    public List<Order> getOrdersByUserIdWithSlave1(long userId) {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    @Read2
    public List<Order> getOrdersByUserIdWithSlave2(long userId) {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    @Write
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }
}
