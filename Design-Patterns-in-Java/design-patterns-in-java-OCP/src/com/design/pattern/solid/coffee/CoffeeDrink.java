package com.design.pattern.solid.coffee;


public class CoffeeDrink extends Coffee{

	private CoffeeSelection selection;
	
	private double quantity;

	public CoffeeDrink(String name, double quantity) {
		super(name, quantity);
	}


	public CoffeeSelection getSelection() {
		return selection;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) throws CoffeeException {
		if (quantity >= 0.0) {
			this.quantity = quantity;
		} else {
			throw new CoffeeException("Quantity has to be >= 0.0.");
		}
	}
}
