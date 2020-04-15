package com.hugui;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StreamApplication.class).web(true).run(args);
    }

}
