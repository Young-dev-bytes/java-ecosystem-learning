package com.problems;

import java.util.Date;
import java.util.List;

public class CustomerTransaction {

    private final List<Product> products;

    private final Customer customer;

    public CustomerTransaction(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
    }


    // reporting
    public String getName() {
        return customer.getName();
    }

    public Date getDate() {
        return new Date();
    }

    public String productBreakDown() {
        StringBuilder reportList = new StringBuilder();
        for (Product product : products) {
            reportList.append(product.getProductName());

        }
        return reportList.toString();
    }


    // transaction
    public void prepareInvoice() {
        System.out.println("invoice prepared...");
    }

    public void chargeCustomer() {
        System.out.println("charge the customer...");
    }

}
