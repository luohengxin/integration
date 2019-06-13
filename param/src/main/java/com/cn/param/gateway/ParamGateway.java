package com.cn.param.gateway;


import com.cn.param.model.Param;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ParamGateway {

    @Gateway(requestChannel = "inputChannel",headers = {@GatewayHeader(name = "user",value = "hanmeimei")})
    void sendMessage(Param param);

}
