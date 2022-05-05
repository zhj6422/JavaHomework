package io.zhj6422.zhjrpc.demo.provider;

import io.zhj6422.zhjrpc.demo.api.Order;
import io.zhj6422.zhjrpc.demo.api.OrderService;

public class OrderServiceImpl implements OrderService {

  @Override
  public Order findOrderById(int id) {
    return new Order(id, "Order" + System.currentTimeMillis(), 9.9f);
  }
}
