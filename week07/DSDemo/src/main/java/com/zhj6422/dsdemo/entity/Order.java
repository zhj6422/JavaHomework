package com.zhj6422.dsdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long user_id;
    @Column(name = "commodities")
    private String commodities;
    @Column(name = "order_price")
    private int order_price;
    @Column(name = "status")
    private int status;
    @Column(name = "create_time")
    private long create_time;
    @Column(name = "update_time")
    private long update_time;
}
