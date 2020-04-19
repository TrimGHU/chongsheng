package com.hugui.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author hugui
 */

@Slf4j
@EnableBinding(ISourceReceiver.class)
public class EmailReceiver {

    @StreamListener(ISourceReceiver.EMAIL_INPUT)
    public void receive(Message<String> message) {
        log.info("email input received, info : " + message);
    }

}
