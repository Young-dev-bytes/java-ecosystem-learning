package com.clients.dao;

import com.clients.domain.Employee;

public class EmployeeDao {

    public void saveEmployee(Employee employee) {
        System.out.println("saved: " + employee.getName());

    }

    public void deleteEmployee(Employee employee) {
        System.out.println("deleted: " + employee.getName());
    }
}
