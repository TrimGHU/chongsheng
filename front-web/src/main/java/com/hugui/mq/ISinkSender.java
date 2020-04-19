package com.hugui.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author  hugui
 */


public interface ISinkSender {

    String ADD_USER_OUTPUT = "add_user_output";

    /**
     * 添加用户的mq输出
     * @return
     */
    @Output(ADD_USER_OUTPUT)
    MessageChannel addUserOutput();
}
