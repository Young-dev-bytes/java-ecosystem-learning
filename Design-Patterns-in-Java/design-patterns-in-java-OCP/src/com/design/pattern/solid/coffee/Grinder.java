package com.design.pattern.solid.coffee;



public class Grinder {

	public GroundCoffee grind(CoffeeBean coffeeBean, double quantityCoffee) {
		return new GroundCoffee(coffeeBean.getName(), quantityCoffee);
	}

}
