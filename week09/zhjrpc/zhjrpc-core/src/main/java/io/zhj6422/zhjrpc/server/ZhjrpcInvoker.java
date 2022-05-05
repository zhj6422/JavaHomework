package io.zhj6422.zhjrpc.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.zhj6422.zhjrpc.api.ZhjrpcResolver;
import io.zhj6422.zhjrpc.api.ZhjrpcRequest;
import io.zhj6422.zhjrpc.api.ZhjrpcResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ZhjrpcInvoker {

  private ZhjrpcResolver resolver;

  public ZhjrpcInvoker(ZhjrpcResolver resolver){
    this.resolver = resolver;
  }

  public ZhjrpcResponse invoke(ZhjrpcRequest request) {
    ZhjrpcResponse response = new ZhjrpcResponse();
    String serviceClass = request.getServiceClass();

    // 作业1：改成泛型和反射
    Object service = resolver.resolve(serviceClass);//this.applicationContext.getBean(serviceClass);

    try {
      Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
      Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
      // 两次json序列化能否合并成一个
      response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
      response.setStatus(true);
      return response;
    } catch ( IllegalAccessException | InvocationTargetException e) {

      // 3.Xstream

      // 2.封装一个统一的RpcfxException
      // 客户端也需要判断异常
      e.printStackTrace();
      response.setException(e);
      response.setStatus(false);
      return response;
    }
  }

  private Method resolveMethodFromClass(Class<?> klass, String methodName) {
    return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
  }

}
