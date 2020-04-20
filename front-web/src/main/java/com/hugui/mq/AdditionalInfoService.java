package com.hugui.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author hugui
 */

@Component
@Slf4j
@EnableBinding(ISinkSender.class)
public class AdditionalInfoService implements IAdditionalInfoService{

    @Autowired
    @Output(ISinkSender.ADD_USER_OUTPUT)
    private MessageChannel messageSender;

    public void addUserEvent(String username, String password) {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
        log.info("add user event send the user info : " + username + "==" + password);
        messageSender.send(MessageBuilder.withPayload(json.toJSONString()).build(), 2000);
    }

}
