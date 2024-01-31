package com.solves;

public class AccountsReceivable {

    // private CustomerTransaction customerTransaction;
    private Accounting customerTransaction;

    /*public AccountsReceivable(CustomerTransaction customerTransaction) {
        this.customerTransaction = customerTransaction;
    }*/

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
