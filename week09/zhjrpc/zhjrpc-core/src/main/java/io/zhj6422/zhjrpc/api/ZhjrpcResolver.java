package io.zhj6422.zhjrpc.api;

public interface ZhjrpcResolver {

    Object resolve(String serviceClass);

    <T> T resolve(Class<T> klass);

}
