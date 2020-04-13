package com.hugui;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
////////////////////////////////////////////
//组合注解，并且加入的ribbon的负载均衡的配置
//@EnableCircuitBreaker
//@EnableDiscoveryClient
////////////////////////////////////////////
public class ZuulServerApplication {

    public static void main(String[] args) {

        //SpringApplication.run(ZullServerApplication.class, args);
        new SpringApplicationBuilder(ZuulServerApplication.class).web(true).run(args);

    }
}
