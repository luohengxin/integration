package com.cn.cafe;


import com.cn.cafe.constant.DrinkType;
import com.cn.cafe.model.Cafe;
import com.cn.cafe.model.Drink;
import com.cn.cafe.model.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CafeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(CafeApplication.class);
        Cafe cafe = (Cafe) app.getBean("cafe");
        // 准备 发送100条消息(订单)
        for (int i = 1; i <= 100; i++) {
            Order order = new Order();
            //  一杯热饮  参数说明1.饮料类型 2.数量 3.是否是冷饮(true表示冷饮)
            order.addDrink(new Drink(DrinkType.LATTE, 2, false));
            //  一杯冷饮  参数说明1.饮料类型 2.数量 3.是否是冷饮(true表示冷饮)
            order.addDrink(new Drink(DrinkType.MOCHA, 3, true));
            // 下发订单，把消息发给 orders 队列
            cafe.placeOrder(order);
        }
    }
}
