package org.example;

import org.example.factory.NYStylePizzaStore;
import org.example.factory.SimplePizzaStore;
import org.example.factory.SimplePizzaFactory;

public class PizzaTest {
    public static void main(String[] args) {
        // =============== 简单工厂 ================
        SimplePizzaFactory factory = new SimplePizzaFactory();
        SimplePizzaStore pizzaStore = new SimplePizzaStore(factory);
        pizzaStore.orderPizza("cheese");
        pizzaStore.orderPizza("pepperoni");
        // ================ 工厂模式 ================
        System.out.println("=============工厂模式==============");
        NYStylePizzaStore nyStylePizzaStore = new NYStylePizzaStore();
        nyStylePizzaStore.orderPizza("NYStyleCheese");
        nyStylePizzaStore.orderPizza("cheese");
    }
}
