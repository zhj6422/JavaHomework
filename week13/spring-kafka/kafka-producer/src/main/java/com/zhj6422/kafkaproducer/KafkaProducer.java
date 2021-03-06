package com.zhj6422.kafkaproducer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhj6422.kafkaproducer.entity.Message;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private Gson gson = new GsonBuilder().create();

  //发送消息方法
  public void send() {
    Message message = new Message();
    message.setId(System.currentTimeMillis());
    message.setMsg(UUID.randomUUID().toString());
    message.setSendTime(new Date());
    log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
    //topic-ideal为主题
    kafkaTemplate.send("topic-test", gson.toJson(message));
  }
}
