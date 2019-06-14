package com.cn.rabbitmq.config;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.inbound.AmqpMessageSource;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Configuration
public class RabbitMqConfiguration {

    @Autowired
    private ConnectionFactory connectionFactory;


    @Autowired
    private AmqpTemplate amqpTemplate;


    @Bean
    public MessageSource<Object> source(){//从中间件拉取数据
        AmqpMessageSource amqpMessageSource = new AmqpMessageSource(connectionFactory,"integration-queue");
        return amqpMessageSource;
    }


    @Bean
    public MessageHandler target(){//向中间件发送
        AmqpOutboundEndpoint amqpOutboundEndpoint = new AmqpOutboundEndpoint(amqpTemplate) ;
        amqpOutboundEndpoint.setExchangeName("integration-exchange");
        amqpOutboundEndpoint.setRoutingKey("integration.test");
        return amqpOutboundEndpoint;
    }

    @Bean
    public MessageChannel middleChannel(){
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow send(){
        return IntegrationFlows.from(middleChannel())
                .handle(target())
                .get();
    }

    @Bean
    public IntegrationFlow receive(){
        return IntegrationFlows.from(source(),s->s.poller(Pollers.fixedRate(1000)))
                .handle(new MessageHandler() {
                    @Override
                    public void handleMessage(Message<?> message) throws MessagingException {
                        System.out.println(message.getPayload());
                    }
                })
                .get();
    }

}
