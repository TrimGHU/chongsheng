package com.hugui.service;

import com.hugui.entity.User;

/**
 * @author hugui
 */

public interface IUserService {

    /**
     * 新增接口
     * @param user
     * @return
     */
    long add(User user);

}