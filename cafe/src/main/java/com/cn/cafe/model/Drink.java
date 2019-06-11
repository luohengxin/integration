package com.cn.cafe.model;

import com.cn.cafe.constant.DrinkType;

public class Drink {

    private DrinkType type;

    private int number;

    private boolean iced;

    public DrinkType getType() {
        return type;
    }

    public void setType(DrinkType type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isIced() {
        return iced;
    }

    public void setIced(boolean iced) {
        this.iced = iced;
    }


    public Drink(DrinkType type, int number, boolean iced) {
        this.type = type;
        this.number = number;
        this.iced = iced;
    }


    @Override
    public String toString() {
        return "Drink{" +
                "type=" + type +
                ", number=" + number +
                ", iced=" + iced +
                '}';
    }
}
