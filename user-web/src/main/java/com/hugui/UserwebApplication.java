package com.hugui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
/////////////////////////////////////////////////////////////
//也可用声明式定义Ribbon
//@RibbonClient(name="xxxx", configuration=MySelfRule.class)
/////////////////////////////////////////////////////////////
public class UserwebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserwebApplication.class).web(true).run(args);
    }

}
