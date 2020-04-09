package com.hugui.web;

import com.hugui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public UserService userService;

    @GetMapping("/name-ribbin")
    public String getUserNameRibbin(){
        return restTemplate.getForObject("http://user/name",String.class);
    }

    @GetMapping("/name-feign")
    public String getUserNameFeign(){
        return userService.getName();
    }

}
