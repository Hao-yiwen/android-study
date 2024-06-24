package org.example.duck;

import org.example.duck.Duck;
import org.example.duck.FlyNoWay;
import org.example.duck.Quack;

public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}
