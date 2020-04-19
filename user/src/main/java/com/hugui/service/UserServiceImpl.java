package com.hugui.service;

import ch.qos.logback.classic.Logger;
import com.hugui.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author hugui
 */

@Service
@Slf4j
public class UserServiceImpl implements IUserService{

    @Override
    public long add(User user) {
        log.info("Add user : " + user.getUsername() + " = " + user.getPassword());
        return new Random(100).nextInt();
    }
}
