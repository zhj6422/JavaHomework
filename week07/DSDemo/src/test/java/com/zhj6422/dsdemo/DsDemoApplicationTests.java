package com.zhj6422.dsdemo;

import com.zhj6422.dsdemo.entity.Order;
import com.zhj6422.dsdemo.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.List;

@SpringBootTest
@MapperScan("com.zhj6422.dsdemo.dao")
class DsDemoApplicationTests {
	@Autowired
	private OrderService orderService;

	@Test
	void contextLoads() {
	}

	@Test
	void testSelectFromSlave1(){
		List<Order> orders = orderService.getOrdersByUserIdWithSlave1(1);
		for (Order order : orders) {
			System.out.println(order);
		}
	}

	@Test
	void testSelectFromSlave2(){
		List<Order> orders = orderService.getOrdersByUserIdWithSlave2(1);
		for (Order order : orders) {
			System.out.println(order);
		}
	}

	@Test
	void testInsert(){
		Order order = new Order();
		order.setUser_id(2);
		order.setCommodities("{‘book’：1}");
		order.setStatus(1);
		Date time = new Date();
		order.setCreate_time(time.getTime());
		order.setUpdate_time(time.getTime());
		orderService.insertOrder(order);
	}

}
