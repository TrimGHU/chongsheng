package com.hugui.configuration;


import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;

/**
 * 配置zuul由于服务问题而导致的异常处理
 */

@Configuration
public class FallbackConfiguration implements ZuulFallbackProvider {

    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return null;
    }
}
