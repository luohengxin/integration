package com.cn.cafe.service;

import com.cn.cafe.model.Drink;
import com.cn.cafe.model.Order;
import org.springframework.integration.annotation.Splitter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderSplitterService {

    @Splitter(inputChannel = "orders",outputChannel = "drinks")
    public List<Drink> split(Order order) {
        return  order.getDrinks();
    }

}
