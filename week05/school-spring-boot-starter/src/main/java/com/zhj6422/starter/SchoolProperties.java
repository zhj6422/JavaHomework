package com.zhj6422.starter;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="school")
public class SchoolProperties {
  private List<String> studentNames;
  private List<Integer> studentIds;
  private List<Integer> klassIds;
  private List<String> klassNames;
  private List<Map<String, Integer>> studentOfKlass;
}
