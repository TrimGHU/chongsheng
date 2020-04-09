package com.hugui.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserService {

    @Override
    public String getName() {
        return "unkown account";
    }
}