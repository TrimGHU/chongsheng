package com.hugui.mq;

/**
 * @author HuGui
 */


public interface IAdditionalInfoService {

    /**
     * 触发添加用户成功的事件
     * @param username
     * @param password
     */
    void addUserEvent(String username, String password);

}
