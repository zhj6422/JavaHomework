package io.zhj6422.zhjrpc.api;

import java.util.List;

public interface Router {

    List<String> route(List<String> urls);
}
