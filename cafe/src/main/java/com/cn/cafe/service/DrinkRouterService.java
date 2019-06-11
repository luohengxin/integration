package com.cn.cafe.service;


import com.cn.cafe.model.Drink;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class DrinkRouterService {

    @Router(inputChannel = "drinks")
    public String route(Drink drink){
        return drink.isIced()?"coldDrinks":"hotDrinks";
    }

}
