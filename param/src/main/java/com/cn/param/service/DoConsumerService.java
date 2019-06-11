package com.cn.param.service;


import com.cn.param.model.Param;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class DoConsumerService {


    @ServiceActivator(inputChannel = "newChannel" )
    public void printOldMsg(Param param) {
        System.out.println("--------处理Old通道信息：" + param);
    }

    @ServiceActivator(inputChannel = "oldChannel" )
    public void printNewMsg(Param param) {
        System.out.println("--------处理New通道信息：" + param);
    }


}
