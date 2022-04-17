package com.zhj6422.dbdemo01;

import com.zhj6422.dbdemo01.bean.Person;
import com.zhj6422.dbdemo01.service.PersonService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Dbdemo01Application.class)
@RunWith(SpringRunner.class)
public class TestSharding {

  @Autowired
  private PersonService personService;

  @Test
  public void testAdd() {
    Person person = new Person();
    person.setName("zhj6422");
    person.setAge(18);
    personService.add(person);
  }

  @Test
  public void testFind() {
    List<Person> all = personService.findAll();
    all.forEach(System.out::println);
  }
}
