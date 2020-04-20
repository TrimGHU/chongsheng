package com.hugui.web;

import com.hugui.mq.IAdditionalInfoService;
import com.hugui.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author hugui
 */

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public IAccountService accountService;

    @Autowired
    public IAdditionalInfoService addInfoService;

    @GetMapping("/name-robbin")
    public String getUserNameRobbin(){
        return restTemplate.getForObject("http://user/name",String.class);
    }

    @GetMapping("/name-feign")
    public String getUserNameFeign(){
        return accountService.getName();
    }

    @GetMapping("/add")
    public long addUser(@RequestParam("username") String username, @RequestParam("password") String password){
        long userId = accountService.add(username, password);

        log.info("add account success : " + username + " == " +password);
        addInfoService.addUserEvent(username,password);
        return userId;
    }


}
