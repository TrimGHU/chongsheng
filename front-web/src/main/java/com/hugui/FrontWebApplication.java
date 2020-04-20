package com.hugui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableFeignClients
/////////////////////////////////////////////////////////////
//也可用声明式定义Ribbon
//@RibbonClient(name="xxxx", configuration=MySelfRule.class)
/////////////////////////////////////////////////////////////

//@SpringCloudApplication
/////////////////////////////
//@SpringCoulldApplication组合注解代理了下面三种
//@EnableDiscoveryClient
//@SpringBootApplication
//@EnableCircuitBreaker
/////////////////////////////

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class FrontWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FrontWebApplication.class).web(true).run(args);
    }

}
