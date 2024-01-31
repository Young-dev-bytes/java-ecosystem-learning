package com.solves;

public class AccountsReceivable {

    private final Accounting customerTransaction;

    public AccountsReceivable(Accounting customerTransaction) {
        this.customerTransaction = customerTransaction;
    }

    public void postPayment() {
        customerTransaction.chargeCustomer();
    }

    public void sendInvoice() {
        customerTransaction.prepareInvoice();
        // send the invoice
    }
}
