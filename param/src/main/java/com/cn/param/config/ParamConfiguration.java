package com.cn.param.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ParamConfiguration {


    @Bean
    public MessageChannel inputChannel(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel afterSplitChannel(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel newChannel(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel oldChannel(){
        return new DirectChannel();
    }


    @Bean
    public MessageChannel replyChannel(){
        return new PublishSubscribeChannel();
    }

}
