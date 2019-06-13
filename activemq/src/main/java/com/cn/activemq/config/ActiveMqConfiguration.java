package com.cn.activemq.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.jms.JmsDestinationPollingSource;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Map;


@Configuration
public class ActiveMqConfiguration {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Bean
    public MessageChannel middleChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageSource<Object> source(){
        JmsDestinationPollingSource jmsDestinationPollingSource = new JmsDestinationPollingSource(jmsTemplate);
        jmsDestinationPollingSource.setDestinationName("integration");
        return jmsDestinationPollingSource;
    }

    @Bean
    public IntegrationFlow receive(){
        return IntegrationFlows
                .from(source(),s -> s.poller(Pollers.fixedDelay(10)))
                .handle(new GenericHandler<Object>() {
                    @Override
                    public Object handle(Object payload, Map<String, Object> headers) {

                        System.out.println(payload);
                        return null;
                    }
                })
                .get();
    }

    @Bean
    public MessageHandler target(){
        JmsSendingMessageHandler sendingMessageHandler = new JmsSendingMessageHandler(jmsTemplate);
        sendingMessageHandler.setDestinationName("integration");
        return sendingMessageHandler;
    }



    @Bean
    public IntegrationFlow send(){
        return IntegrationFlows
                .from(middleChannel())
                .handle(target())
                .get();
    }
}
