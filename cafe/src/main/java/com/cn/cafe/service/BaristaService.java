package com.cn.cafe.service;


import com.cn.cafe.model.Drink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class BaristaService {


    @ServiceActivator(inputChannel = "coldDrinks")
    public void prepareClodDrink(Drink drink) {
        System.out.println(Thread.currentThread().getName()
                                      +   "  prepared clod drink #   for order # " +drink);
    }

    @ServiceActivator(inputChannel = "hotDrinks")
    public void prepareHotDrink(Drink drink) {
        System.out.println(Thread.currentThread().getName()
                                      +   "  prepared hot drink #   for order # " +drink);
    }


}
