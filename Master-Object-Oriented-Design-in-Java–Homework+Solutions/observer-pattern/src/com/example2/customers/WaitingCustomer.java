package com.example2.customers;

// Observer
public interface WaitingCustomer {

    void orderReady(String preparedDrink);

    String getName();

    String getDrinkOrdered();
}
