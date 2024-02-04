package com.problems;

public class AccountsReceivable {

    private final CustomerTransaction customerObject;

    public AccountsReceivable(CustomerTransaction customerObject) {
        this.customerObject = customerObject;
    }

    public void postPayment() {
        customerObject.chargeCustomer();
    }

    public void sendInvoice() {
        customerObject.prepareInvoice();
        // send the invoice
    }
}
