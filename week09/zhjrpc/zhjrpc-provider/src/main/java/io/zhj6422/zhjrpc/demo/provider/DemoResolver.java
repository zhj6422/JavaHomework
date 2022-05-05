package io.zhj6422.zhjrpc.demo.provider;

import io.zhj6422.zhjrpc.api.ZhjrpcResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements ZhjrpcResolver, ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public Object resolve(String serviceClass) {
    return this.applicationContext.getBean(serviceClass);
  }
}