package com.zhj6422.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(SchoolProperties.class)
@ConditionalOnClass(School.class)
@ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
@PropertySource("classpath:application.properties")
public class SchoolAutoConfiguration {

  @Autowired
  private SchoolProperties props;

  @Bean
  public School school(){
    List<Integer> studentIds = props.getStudentIds();
    List<String> studentNames = props.getStudentNames();
    List<Integer> klassIds = props.getKlassIds();
    List<String> klassNames = props.getKlassNames();
    List<Map<String, Integer>> studentOfKlass = props.getStudentOfKlass();

    List<Student> students = new ArrayList<>(studentIds.size());
    for(int i = 0; i < studentIds.size(); i++){
      students.add(new Student(studentIds.get(i),studentNames.get(i)));
    }
    List<Klass> klasses = new ArrayList<>(klassIds.size());
    for(int i = 0; i < klassIds.size(); i++){
      klasses.add(new Klass(klassIds.get(i), klassNames.get(i)));
    }
    for(Map map: studentOfKlass){
      klasses.get((Integer) map.get("klassId")).addStudent(students.get(
          (Integer) map.get("studentId")));
    }

    System.out.println(studentIds);
    System.out.println(studentNames);
    System.out.println(klassIds);
    System.out.println(klassNames);
    System.out.println(students);
    System.out.println(klasses);

    return new School(klasses);
  }

}
