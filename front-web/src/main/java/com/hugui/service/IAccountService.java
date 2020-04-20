package com.hugui.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author  hugui
 */

@FeignClient(name = "user", fallback = AccountServiceFallbackImpl.class)
public interface IAccountService {

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
    long add(@RequestParam("username")String username, @RequestParam("password")String password);
}