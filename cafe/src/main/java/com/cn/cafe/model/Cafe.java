package com.cn.cafe.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class Cafe {

    @Autowired
    private MessageChannel orders;

    public void  placeOrder(Order order){

        orders.send(new GenericMessage<Object>(order));
    }

}
