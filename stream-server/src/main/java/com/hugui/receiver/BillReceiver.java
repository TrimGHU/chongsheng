package com.hugui.receiver;

import com.hugui.entity.Bill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
public class BillReceiver {

    @StreamListener(Sink.INPUT)
    public void receiver(Bill bill){
        log.info(bill.getUsername() + " cost money : " + bill.getTotal());
    }

}
