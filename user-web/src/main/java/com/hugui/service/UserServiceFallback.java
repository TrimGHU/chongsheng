package com.hugui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceFallback.class);

    @Override
    public String getName() {
        log.error("Your remote request 'getName' is invalid");
        return "unkown account";
    }
}