package io.zhj6422.zhjrpc.api;

import lombok.Data;
/*
 * 请求
 * serviceClass：请求的类名
 * method：请求的方法
 * params：请求带的参数
 * */
@Data
public class ZhjrpcRequest {
  private String serviceClass;
  private String method;
  private Object[] params;
}
