package com.cn.cafe.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class CafeConfiguration {

    @Bean
    public MessageChannel orders(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel drinks(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel coldDrinks(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel hotDrinks(){
        return new DirectChannel();
    }
}
