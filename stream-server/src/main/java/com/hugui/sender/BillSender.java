package com.hugui.sender;

import com.hugui.entity.Bill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

@Slf4j
@EnableBinding({Source.class})
public class BillSender {

    @Bean
    @InboundChannelAdapter(value=Source.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Bill> sender(){
        return () -> new GenericMessage<Bill>(Bill.builder().username("hugui").total(1000).build());
    }

}
