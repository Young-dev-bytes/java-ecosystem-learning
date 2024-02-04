package com.example2.coffeeshop;

import com.example2.customers.Customer;

import java.util.ArrayList;
import java.util.List;

// subject
public class CoffeeAttendent {


    // observer
    private List<Customer> customersList;
    private List<String> completedDrinks;

    public CoffeeAttendent() {
        customersList = new ArrayList<Customer>();
        completedDrinks = new ArrayList<String>();
    }


    // register
    public void takeOrder(Customer customer) {
        customersList.add(customer);
        System.out.println("Coffee Attendent: '" + customer.getName()
                + ", I've started working on your order of "
                + customer.getDrinkOrdered() + "'");
    }


    public void prepareDrink(String drinkToBePrepared) {
        double timeTaken = Math.random() * 10; // give value between 1 and 50
        try {
            Thread.sleep((long) (timeTaken) * 1000);
            completedDrinks.add(drinkToBePrepared);
        } catch (InterruptedException e) {
            System.out.println(" for some reason, drink was not prepared..");
        }
    }


    // notify
    public void callOutCompletedOrders() {
        for (String readyDrink : completedDrinks) {
            System.out.println("Order up: " + readyDrink);
            for (Customer customer : customersList) {
                customer.orderReady(readyDrink);
            }
        }
    }
}
