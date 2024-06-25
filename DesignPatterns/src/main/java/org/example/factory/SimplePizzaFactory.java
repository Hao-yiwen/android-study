package org.example.factory;

public class SimplePizzaFactory {
    public Pizza createPizza(String type){
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if(type.equals("cheese")){
            pizza = new CheesePizza(ingredientFactory);
        } else if(type.equals("pepperoni")){
            pizza = new PepperoniPizza(ingredientFactory);
        } else if(type.equals("chicagoStyle")){
            pizza = new ChicagoStyleCheesePizza(ingredientFactory);
        } else if(type.equals("NYStyleCheese")){
            pizza = new NYStyleCheesePizza(ingredientFactory);
        }
        return pizza;
    }
}
