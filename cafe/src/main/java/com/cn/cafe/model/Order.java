package com.cn.cafe.model;

import java.util.ArrayList;
import java.util.List;

public class Order {


    private List<Drink> drinks = new ArrayList<>();


    public void addDrink(Drink drink){
        drinks.add(drink);
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}
