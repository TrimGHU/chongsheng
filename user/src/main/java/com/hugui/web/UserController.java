package com.hugui.web;

import com.hugui.entity.User;
import com.hugui.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hugui
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/name")
    public String getName() {
        return "My name is Hello World";
    }

    @GetMapping("/add")
    public long add(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("user controller add user : " + username + "-" + password);
        return userService.add(username,password);
    }
}
