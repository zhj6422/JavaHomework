package com.zhj6422.beanCode.model;

import java.util.List;

public class Klass {

  List<Student> students;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public void dong(){
    System.out.println(this.getStudents());
  }
}
