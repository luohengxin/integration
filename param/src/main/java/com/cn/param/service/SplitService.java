package com.cn.param.service;


import com.cn.param.model.Param;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SplitService {

    @Splitter(inputChannel = "inputChannel",outputChannel = "afterSplitChannel")
    protected Object splitMessage(Message<?> msg) {
        if(msg.getPayload() == null) {
            return null;
        }

        Param param = (Param)msg.getPayload();

        if(param.getDataType() !=0 && param.getDataType() !=1) {
            return null;
        }

        //可以重新对param进行处理后再返回
        return param;

    }

}
