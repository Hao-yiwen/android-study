package org.example.decorator;

public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription(){
        return this.description;
    }

    public abstract double cost();

    public abstract DrinkSizeEnum size();
}
