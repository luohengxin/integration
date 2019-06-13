package com.cn.jdbc.service;

import com.cn.jdbc.model.Person;
import org.springframework.messaging.support.GenericMessage;

import java.util.List;

public class SplitterService {


    public List<Person> Splitter(GenericMessage<List<Person>> message){
        List<Person> payload = message.getPayload();
        return payload;

    }
}
