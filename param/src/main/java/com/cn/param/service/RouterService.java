package com.cn.param.service;

import com.cn.param.model.Param;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;


@Component
public class RouterService {


    @Router(inputChannel = "afterSplitChannel")
    public String getDistritChannelId(Param param) {
        if (param.getDataType() == 0) {
            return "oldChannel";
        }
        if (param.getDataType() == 1) {
            return "newChannel";
        }
        return "oldChannel";
    }
}
