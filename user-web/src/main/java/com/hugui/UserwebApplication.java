package com.hugui;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableFeignClients
/////////////////////////////////////////////////////////////
//也可用声明式定义Ribbon
//@RibbonClient(name="xxxx", configuration=MySelfRule.class)
/////////////////////////////////////////////////////////////

@SpringCloudApplication
/////////////////////////////
//@SpringCoulldApplication组合注解代理了下面三种
//@EnableDiscoveryClient
//@SpringBootApplication
//@EnableCircuitBreaker
/////////////////////////////
public class UserwebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserwebApplication.class).web(true).run(args);
    }

}
