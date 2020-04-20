package com.hugui.service;


/**
 * @author hugui
 */

public interface IUserService {

    /**
     * 新增接口
     * @param user
     * @return
     */
    long add(String username, String password);

}