package com.zhj6422.dbdemo01;

import com.zhj6422.dbdemo01.bean.Person;
import com.zhj6422.dbdemo01.service.PersonService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Dbdemo01ApplicationTests {

  @Autowired
  private PersonService personService;

  @Test
  public void contextLoads() {
    Person person = new Person();
    person.setName("zhj6422");
    person.setAge(18);
    personService.add(person);
  }

  @Test
  public void testQuery() {
    List<Person> all = personService.findAll();
    all.forEach(System.out::println);
  }


}
