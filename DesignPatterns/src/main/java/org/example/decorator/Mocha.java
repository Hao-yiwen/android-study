package org.example.decorator;

public class Mocha extends BaseCondimentDecorator{

    public Mocha(Beverage beverage){
        super(beverage);
    }

    @Override
    public double cost() {
        DrinkSizeEnum staticSize = this.beverage.size();
        if (staticSize == DrinkSizeEnum.VENTI) {
            return this.beverage.cost() + .20;
        } else if (staticSize == DrinkSizeEnum.GRANDE) {
            return this.beverage.cost() + .15;
        } else {
            return this.beverage.cost() + .10;
        }
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
}
