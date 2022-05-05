package io.zhj6422.zhjrpc.api;

import lombok.Data;
/*
* 响应
* result：响应结果
* status：状态，成功或者失败
* exception：存在的异常
* */
@Data
public class ZhjrpcResponse {
  private Object result;
  private boolean status;
  private Exception exception;
}
