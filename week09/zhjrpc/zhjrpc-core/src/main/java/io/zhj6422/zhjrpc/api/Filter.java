package io.zhj6422.zhjrpc.api;

public interface Filter {

    boolean filter(ZhjrpcRequest request);

    // Filter next();

}
