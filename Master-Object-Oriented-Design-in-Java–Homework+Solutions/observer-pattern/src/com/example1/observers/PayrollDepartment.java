package com.before.observers;

import com.before.domain.Employee;

public class PayrollDepartment implements IObserver{

    @Override
    public void callMe(Employee emp, String msg) {
        System.out.println("PAYROLL department notified...");
        System.out.println(msg + ": " + emp.getName());
    }

}
