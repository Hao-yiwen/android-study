package org.example.duck;

import org.example.duck.Duck;
import org.example.duck.FlyWithWings;
import org.example.duck.Quack;

public class MallardDuck extends Duck {
    public MallardDuck(){
        super();
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void performFly() {
        super.performFly();
    }

    @Override
    public void performQuack() {
        super.performQuack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
