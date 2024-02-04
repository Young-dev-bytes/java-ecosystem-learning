package com.problems;

public class ReportGenerator {

    private final CustomerTransaction customerObject;

    public ReportGenerator(CustomerTransaction customerObject) {
        this.customerObject = customerObject;
    }

    public void generateReport() {
        System.out.println(customerObject.getName() + " - "
                + customerObject.productBreakDown() + " - "
                + customerObject.getDate());

    }
}
