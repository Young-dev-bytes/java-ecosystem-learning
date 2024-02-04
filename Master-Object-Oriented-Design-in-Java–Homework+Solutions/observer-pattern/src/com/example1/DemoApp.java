package com.example1;

import com.example1.domain.Employee;
import com.example1.observers.HRDepartment;
import com.example1.observers.IObserver;
import com.example1.observers.ITDepartment;
import com.example1.observers.PayrollDepartment;
import com.example1.subject.EmployeeManagementSystem;

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
