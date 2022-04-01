package com.zhj6422.beanCode.model;

import com.zhj6422.beanCode.aop.ISchool;
import javax.annotation.Resource;

public class School implements ISchool {
  // Resource
  @Resource //primary
      Klass class1;

  @Resource(name = "student100")
  Student student100;

  @Override
  public void ding(){

    System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);

  }

}
