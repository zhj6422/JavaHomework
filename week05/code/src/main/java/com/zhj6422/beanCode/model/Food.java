package com.zhj6422.beanCode.model;

import org.springframework.stereotype.Component;

// 通过注解配置bean
@Component("food")
public class Food {

  private int foodId;
  private String foodName;

  public int getFoodId() {
    return foodId;
  }

  public void setFoodId(int foodId) {
    this.foodId = foodId;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public Food() {
    this.foodId = 1;
    this.foodName = "老坛酸菜";
  }

  public Food(int foodId, String foodName) {
    this.foodId = foodId;
    this.foodName = foodName;
  }

  @Override
  public String toString() {
    return "Food{" +
        "foodId=" + foodId +
        ", foodName='" + foodName + '\'' +
        '}';
  }
}
