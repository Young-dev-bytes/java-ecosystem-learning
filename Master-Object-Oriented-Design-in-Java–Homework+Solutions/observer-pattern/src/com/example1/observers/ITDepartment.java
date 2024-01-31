package com.before.observers;

import com.before.domain.Employee;

public class ITDepartment implements IObserver {
    @Override
    public void callMe(Employee emp, String msg) {
        System.out.println("IT department notified...");
        System.out.println(msg + ": " + emp.getName());
    }
}
