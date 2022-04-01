package com.zhj6422.beanCode;

import com.zhj6422.beanCode.model.Food;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodConfig {

  @Bean
  public Food food(){
    return new Food();
  }
}
