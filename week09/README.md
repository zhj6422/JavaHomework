**3.（必做）**改造自定义 RPC 的程序，提交到 GitHub：

- 尝试将服务端写死查找接口实现类变成泛型和反射；

  ```java
  Class klass = null;
  try { // 通过反射拿到Class
      klass = Class.forName(serviceClass);
  } catch (ClassNotFoundException e) {
      response.setException(e);
      response.setStatus(false);
      e.printStackTrace();
      return response;
  }
  Object service = resolver.resolve(klass);
  ```

- 尝试将客户端动态代理改成 AOP，添加异常处理；

  ```java
  package io.zhj6422.zhjrpc.client;
  
  import com.alibaba.fastjson.JSON;
  import com.alibaba.fastjson.parser.ParserConfig;
  import io.zhj6422.zhjrpc.api.Filter;
  import io.zhj6422.zhjrpc.api.LoadBalancer;
  import io.zhj6422.zhjrpc.api.Router;
  import io.zhj6422.zhjrpc.api.ZhjrpcRequest;
  import io.zhj6422.zhjrpc.api.ZhjrpcResponse;
  import java.io.IOException;
  import java.lang.reflect.InvocationHandler;
  import java.lang.reflect.InvocationTargetException;
  import java.lang.reflect.Method;
  import java.lang.reflect.Proxy;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.concurrent.ConcurrentHashMap;
  
  import io.zhj6422.zhjrpc.exception.ZhjrpcException;
  import net.bytebuddy.ByteBuddy;
  import net.bytebuddy.implementation.InvocationHandlerAdapter;
  import net.bytebuddy.matcher.ElementMatchers;
  import okhttp3.MediaType;
  import okhttp3.OkHttpClient;
  import okhttp3.Request;
  import okhttp3.RequestBody;
  
  public final class Zhjrpc {
  
    static {
      ParserConfig.getGlobalInstance().addAccept("io.zhj6422");
    }
  
    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {
  
      // 加filte之一
  
      // curator Provider list from zk
      List<String> invokers = new ArrayList<>();
      // 1. 简单：从zk拿到服务提供的列表
      // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）
  
      List<String> urls = router.route(invokers);
  
      String url = loadBalance.select(urls); // router, loadbalance
  
      return (T) create(serviceClass, url, filter);
  
    }
  
    private static final ConcurrentHashMap servicesMap = new ConcurrentHashMap();
    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {
      // 0. 替换动态代理 -> 字节码生成
  //    return (T) Proxy.newProxyInstance(Zhjrpc.class.getClassLoader(), new Class[]{serviceClass}, new ZhjrpcInvocationHandler(serviceClass, url, filters));
      T service = null;
      try {
        service = (T)servicesMap.putIfAbsent(serviceClass, creatBytecodeGenerateProxy(serviceClass, url));
      } catch (NoSuchMethodException e) {
        throw new ZhjrpcException(e);
      } catch (InvocationTargetException e) {
        throw new ZhjrpcException(e);
      } catch (InstantiationException e) {
        throw new ZhjrpcException(e);
      } catch (IllegalAccessException e) {
        throw new ZhjrpcException(e);
      }
      if(service == null){
        service = (T) servicesMap.get(serviceClass);
      }
      return service;
    }
  
    private static <T> T creatBytecodeGenerateProxy(Class<T> serviceClass, String url) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
     return (T) new ByteBuddy()
             .subclass(Object.class)
             .implement(serviceClass)
             .method(ElementMatchers.any())
             .intercept(InvocationHandlerAdapter.of(new Zhjrpc.ZhjrpcInvocationHandler(serviceClass, url)))
             .make()
             .load(Zhjrpc.class.getClassLoader())
             .getLoaded()
             .getDeclaredConstructor()
             .newInstance();
    }
  
    public static class ZhjrpcInvocationHandler implements InvocationHandler {
  
      public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
  
      private final Class<?> serviceClass;
      private final String url;
      private final Filter[] filters;
  
      public <T> ZhjrpcInvocationHandler(Class<T> serviceClass, String url, Filter... filters) {
        this.serviceClass = serviceClass;
        this.url = url;
        this.filters = filters;
      }
  
      // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
      // int byte char float double long bool
      // [], data class
  
      @Override
      public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
  
        // 加filter地方之二
        // mock == true, new Student("hubao");
  
        ZhjrpcRequest request = new ZhjrpcRequest();
        request.setServiceClass(this.serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(params);
  
        if (null!=filters) {
          for (Filter filter : filters) {
            if (!filter.filter(request)) {
              return null;
            }
          }
        }
  
        ZhjrpcResponse response = post(request, url);
  
        // 加filter地方之三
        // Student.setTeacher("cuijing");
  
        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException
  
        return JSON.parse(response.getResult().toString());
      }
  
      private ZhjrpcResponse post(ZhjrpcRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);
  
        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
            .url(url)
            .post(RequestBody.create(JSONTYPE, reqJson))
            .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, ZhjrpcResponse.class);
      }
    }
  }
  ```

- 尝试使用 Netty+HTTP 作为 client 端传输方式。

  新建[NettyClient.java]()和[RpcHttpClientHandler.java]()，并在Zhjrpc中调用NettyClient进行网络请求