package com.hugui.web;

import com.hugui.entity.User;
import com.hugui.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hugui
 */

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
        return userService.add(User.builder().username(username).password(password).build());
    }
}
