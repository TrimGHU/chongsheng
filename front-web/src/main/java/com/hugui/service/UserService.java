package com.hugui.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author  hugui
 */

@FeignClient(name = "user", fallback = UserServiceFallbackImpl.class)
public interface UserService {

    /**
     * 获取名称
     * @return 名称
     */
    @GetMapping("/name")
    String getName();

    /**
     * 新增用户
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/add")
    long add(String username, String password);
}