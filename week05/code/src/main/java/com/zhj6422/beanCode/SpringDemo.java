package com.zhj6422.beanCode;

import com.zhj6422.beanCode.aop.ISchool;
import com.zhj6422.beanCode.model.Food;
import com.zhj6422.beanCode.model.Klass;
import com.zhj6422.beanCode.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {



  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    System.out.println("student123:");
    Student student123 = (Student) context.getBean("student123");
    System.out.println(student123.toString());

    System.out.println("student100:");
    Student student100 = (Student) context.getBean("student100");
    System.out.println(student100.toString());

    System.out.println("Food:");
    Food food = (Food) context.getBean("food");
    System.out.println(food.toString());

    System.out.println("Klass:");
    Klass class1 = context.getBean(Klass.class);
    System.out.println(class1);
    System.out.println("Klass对象AOP代理后的实际类型："+class1.getClass());
    System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类："+(class1 instanceof Klass));

    System.out.println("School:");
    ISchool school = context.getBean(ISchool.class);
    System.out.println(school);
    System.out.println("ISchool接口的对象AOP代理后的实际类型："+school.getClass());


    System.out.println("ding:");
    school.ding();

    System.out.println("dong:");
    class1.dong();

    System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));

  }
}
