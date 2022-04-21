package com.zhj6422.dsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zhj6422.dsdemo.dao")
public class DsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsDemoApplication.class, args);
	}

}
