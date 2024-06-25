package org.example.decorator;

public abstract class BaseCondimentDecorator extends CondimentDecorator{
    protected Beverage beverage;

    public BaseCondimentDecorator(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public DrinkSizeEnum size() {
        return this.beverage.size();
    }
}
