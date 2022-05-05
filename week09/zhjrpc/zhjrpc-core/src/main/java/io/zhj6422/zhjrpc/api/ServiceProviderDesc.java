package io.zhj6422.zhjrpc.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceProviderDesc {

    private String host;
    private Integer port;
    private String serviceClass;

    // group
    // version
}
