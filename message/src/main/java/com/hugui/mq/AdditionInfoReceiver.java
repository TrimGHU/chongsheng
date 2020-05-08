package com.hugui.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.util.concurrent.TimeUnit;

/**
 * @author hugui
 */

@Slf4j
@EnableBinding(ISourceReceiver.class)
public class AdditionInfoReceiver {

    @StreamListener(ISourceReceiver.ADDITION_INFO_INPUT)
    public void receive(Message<String> message) {

        log.info("addition info input received, info : " + message);

    }

}
