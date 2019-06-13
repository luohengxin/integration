package com.cn.param;


import com.cn.param.gateway.ParamGateway;
import com.cn.param.model.Param;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class ParamApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ParamApplication.class);
/*        MessageChannel msgChannel = app.getBean("inputChannel",MessageChannel.class);
        Param param = new Param();

        for(int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                param.setDataType(0);
            } else {
                param.setDataType(1);
            }
            param.setData("{\"sum\":" + i + "}");
            Message<Param> msg = MessageBuilder.withPayload(param).build();
            msgChannel.send(msg);
        }*/

        ParamGateway gateway = app.getBean("paramGateway",ParamGateway.class);
        Param param = new Param();

        for(int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                param.setDataType(0);
            } else {
                param.setDataType(1);
            }
            param.setData("{\"sum\":" + i + "}");
            gateway.sendMessage(param);
        }
    }
}
