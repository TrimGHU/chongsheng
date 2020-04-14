package com.hugui.configuration;


import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 配置zuul由于服务问题而导致的异常处理
 * @author hugui
 */

@Configuration
public class FallbackConfiguration implements ZuulFallbackProvider {

    /**
     * 指定当前执行fallback方法的route，如果希望全部则是 return "*"
     * @return
     */
    @Override
    public String getRoute() {
        return "user";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {

        return new ClientHttpResponse(){
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("error: fallback".getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }
        };
    }
}
