package com.solves;

public class ReportGenerator {

    private final Reporting customerObject;

    public ReportGenerator(Reporting customerObject) {
        this.customerObject = customerObject;
    }

    public void generateReport() {
        System.out.println(customerObject.getName() + " - "
                + customerObject.productBreakDown() + " - "
                + customerObject.getDate());
    }
}
