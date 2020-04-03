package com.hugui;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class UserApplication {

    @RequestMapping("/")
    public String sayHi(){
        return "hi";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApplication.class).web(true).run(args);
    }

}
