package com.solves;

import java.util.List;

public class DemoApp {
    public static void main(String[] args) {

        List<Product> products = List.of(
                new Product(101, "p1"),
                new Product(102,"p2")
        );
        CustomerTransaction customerTransaction = new CustomerTransaction(products, new Customer("Young"));

        // accounting
        AccountsReceivable accountsReceivable = new AccountsReceivable(customerTransaction);
        accountsReceivable.postPayment();
        accountsReceivable.sendInvoice();

        // reporting
        ReportGenerator reportGenerator = new ReportGenerator(customerTransaction);
        reportGenerator.generateReport();


    }
}
