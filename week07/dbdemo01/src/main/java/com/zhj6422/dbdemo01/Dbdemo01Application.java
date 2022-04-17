package com.zhj6422.dbdemo01;

import  tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhj6422.dbdemo01.dao")
public class Dbdemo01Application {

  public static void main(String[] args) {
    SpringApplication.run(Dbdemo01Application.class, args);
  }

}
