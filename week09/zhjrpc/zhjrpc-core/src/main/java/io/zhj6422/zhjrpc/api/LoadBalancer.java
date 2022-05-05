package io.zhj6422.zhjrpc.api;

import java.util.List;

public interface LoadBalancer {

    String select(List<String> urls);

}
