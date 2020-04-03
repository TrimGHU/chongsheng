package com.hugui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(EurekaServerApplication.class, args);

        //设置是否为web项目，是否需要web访问，加载资源会有区别
        new SpringApplicationBuilder(EurekaServerApplication.class).web(true).run(args);
    }

}
