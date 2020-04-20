package com.hugui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author hugui
 */

@Component
public class AccountServiceFallbackImpl implements IAccountService {

    private Logger log = LoggerFactory.getLogger(AccountServiceFallbackImpl.class);

    @Override
    public String getName() {
        log.error("Your remote request 'getName' is invalid");
        return "unknown account";
    }

    @Override
    public long add(String username, String password) {
        log.error("Your remote request 'add' is invalid");
        return 0;
    }
}