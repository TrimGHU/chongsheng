package com.hugui.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user", fallback = UserServiceFallback.class)
public interface UserService {

    @GetMapping("/name")
    String getName();

}