package org.example.factory;

public class NYStylePizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if (type.equals("NYStyleCheese")) {
            return new NYStyleCheesePizza(ingredientFactory);
        } else if (type.equals("cheese")) {
            return new CheesePizza(ingredientFactory);
        } else if (type.equals("pepperoni")) {
            return new PepperoniPizza(ingredientFactory);
        } else {
            return new ChicagoStyleCheesePizza(ingredientFactory);
        }
    }
}
