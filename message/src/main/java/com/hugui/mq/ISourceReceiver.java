package com.hugui.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author hugui
 */


public interface ISourceReceiver {
    String EMAIL_INPUT = "email_input";
    String ADDITION_INFO_INPUT = "addition_info_input";

    /**
     * 添加用户触发的发送email
     * @return
     */
    @Input(EMAIL_INPUT)
    SubscribableChannel emailInput();

    /**
     * 添加用户触发的添加附加信息
     * @return
     */
    @Input(ADDITION_INFO_INPUT)
    SubscribableChannel additionInfoInput();

}
