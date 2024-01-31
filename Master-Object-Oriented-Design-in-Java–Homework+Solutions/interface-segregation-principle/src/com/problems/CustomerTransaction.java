package com.solves;

import java.util.Date;

public class CustomerTransaction implements Accounting, Reporting {

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public Date getDate() {
        return new Date();
    }

    @Override
    public String productBreakDown(){
        return "list of products for reporting";
    }

    @Override
    public void prepareInvoice() {
        System.out.println("invoice prepared...");
    }

    @Override
    public void chargeCustomer() {
        System.out.println("charge the customer...");
    }

}
