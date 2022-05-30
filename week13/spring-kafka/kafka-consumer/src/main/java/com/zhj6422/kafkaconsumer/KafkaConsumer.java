package com.zhj6422.kafkaconsumer;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

  @KafkaListener(topics = {"topic-test"})
  public void consumer(ConsumerRecord<?, ?> record){
    Optional<?> kafkaMessage = Optional.ofNullable(record.value());
    if (kafkaMessage.isPresent()) {
      Object message = kafkaMessage.get();
      log.info("----------------- record =" + record);
      log.info("------------------ message =" + message);
    }
  }
}