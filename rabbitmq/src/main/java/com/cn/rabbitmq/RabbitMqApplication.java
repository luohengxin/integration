package com.cn.rabbitmq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RabbitMqApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext app = SpringApplication.run(RabbitMqApplication.class);
        MessageChannel middleChannel = app.getBean("middleChannel", MessageChannel.class);

        Map<String,Object> map = new HashMap<>();
        map.put("user","hanmeimei");
        map.put("age",12);

        middleChannel.send(MessageBuilder.withPayload(map).build());

    }
}
