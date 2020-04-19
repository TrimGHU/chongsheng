package com.hugui.web;

import com.hugui.mq.ISinkSender;
import com.hugui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author hugui
 */

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    public UserService userService;

    @GetMapping("/name-robbin")
    public String getUserNameRobbin(){
        return restTemplate.getForObject("http://user/name",String.class);
    }

    @GetMapping("/name-feign")
    public String getUserNameFeign(){
        return userService.getName();
    }

    @GetMapping("/add")
    public long addUser(@RequestParam("username") String username, @RequestParam("password") String password){
        long userId = userService.add(username, password);

        return 0;
    }


}
