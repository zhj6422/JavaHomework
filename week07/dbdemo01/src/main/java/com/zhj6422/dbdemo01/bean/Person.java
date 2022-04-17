package com.zhj6422.dbdemo01.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
}
