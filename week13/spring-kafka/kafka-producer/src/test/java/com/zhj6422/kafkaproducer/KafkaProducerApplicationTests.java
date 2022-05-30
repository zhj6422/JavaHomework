package com.zhj6422.kafkaproducer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaProducerApplicationTests {

  @Autowired
  private KafkaProducer kafkaProducer;

  @Test
  void contextLoads() {
  }

  @Test
  public void testProducer(){
    this.kafkaProducer.send();
  }

}
