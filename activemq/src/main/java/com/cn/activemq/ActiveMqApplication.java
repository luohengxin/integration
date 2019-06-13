package com.cn.activemq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ActiveMqApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ActiveMqApplication.class);

        MessageChannel middleChannel = app.getBean("middleChannel", MessageChannel.class);
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",12);

        middleChannel.send(MessageBuilder.withPayload(map).build());
    }
}
