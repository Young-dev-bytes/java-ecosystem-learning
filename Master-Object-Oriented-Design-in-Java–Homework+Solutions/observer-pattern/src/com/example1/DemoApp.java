package com.before;

import com.before.domain.Employee;
import com.before.observers.HRDepartment;
import com.before.observers.IObserver;
import com.before.observers.ITDepartment;
import com.before.observers.PayrollDepartment;
import com.before.subject.EmployeeManagementSystem;

import java.util.Date;

public class DemoApp {

    public static void main(String[] args) {

        IObserver payroll = new PayrollDepartment();
        IObserver hrSystem = new HRDepartment();
        ITDepartment itSystem = new ITDepartment();

        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.registerObserver(payroll);
        ems.registerObserver(hrSystem);
        ems.registerObserver(itSystem);

        Employee bob = new Employee("Bob", new Date(), 35000,true);
        ems.hireNewEmployee(bob);

        ems.modifyEmployeeName(5, "Imtiaz");
    }
}
