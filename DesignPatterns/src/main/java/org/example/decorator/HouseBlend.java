package org.example.decorator;

public class HouseBlend extends Beverage{
    public HouseBlend(){
        description = "House Blend Coffee";
    }
    @Override
    public double cost() {
        return .89;
    }

    @Override
    public DrinkSizeEnum size() {
        return DrinkSizeEnum.VENTI;
    }
}
