package com.cn.param.service;


import com.cn.param.model.Param;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class DoConsumerService {


    @ServiceActivator(inputChannel = "newChannel",outputChannel = "replyChannel")
    public String printOldMsg(Param param) {
        System.out.println("--------处理Old通道信息：" + param);
        return "old :" + param.toString();
    }

    @ServiceActivator(inputChannel = "oldChannel",outputChannel = "replyChannel" )
    public String printNewMsg(Param param) {
        System.out.println("--------处理New通道信息：" + param);
        return "new :" + param.toString();
    }



    @ServiceActivator(inputChannel = "replyChannel" )
    public void reply(Object param) {
        System.out.println(param);
    }






}
