package com.zhj6422.starter;

import java.util.List;

public class School {

  private List<Klass> klasses;

  public School(List<Klass> klasses) {
    this.klasses = klasses;
  }

  @Override
  public String toString() {
    return "School{" +
        "klasses=" + klasses +
        '}';
  }
}
