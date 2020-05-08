package com.hugui.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hugui
 */

@Slf4j
@EnableBinding(ISourceReceiver.class)
public class EmailReceiver {

    private AtomicInteger count = new AtomicInteger(0);

    @StreamListener(ISourceReceiver.EMAIL_INPUT)
    public void receive(Message<String> message) {

        log.info("email input received, info : " + message);

        if(count.intValue() < 1){

            count.incrementAndGet();
            throw new AmqpRejectAndDontRequeueException("failed");
        }

        log.info("email input received, success !");
    }

}
