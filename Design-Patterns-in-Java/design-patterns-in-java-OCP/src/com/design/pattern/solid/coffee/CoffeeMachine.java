package com.design.pattern.solid.coffee;

public interface CoffeeMachine {
	
	Coffee brewCoffee(CoffeeSelection selection) throws CoffeeException;
}