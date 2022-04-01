package com.zhj6422.starter;

import java.util.ArrayList;
import java.util.List;

public class Klass {
  private int id;
  private String name;
  private List<Student> students = new ArrayList<>();

  public Klass(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void addStudent(Student student){
    students.add(student);
  }

  @Override
  public String toString() {
    return "Klass{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", students=" + students +
        '}';
  }
}
