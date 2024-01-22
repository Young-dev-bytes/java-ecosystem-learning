package com.clients.modules;

import com.clients.dao.EmployeeDao;
import com.clients.domain.Employee;
import com.clients.formatting.EmployeeReportFormatter;
import com.clients.formatting.FormatType;

public class ClientModule {

    EmployeeDao employeeDao;
    EmployeeReportFormatter employeeReportFormatter;

    public ClientModule(EmployeeDao employeeDao, EmployeeReportFormatter employeeReportFormatter) {
        this.employeeDao = employeeDao;
        this.employeeReportFormatter = employeeReportFormatter;
    }

    public void hireNewEmployee(Employee employee) {
        // EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.saveEmployee(employee);
    }

    public void printEmployeeReport(/*Employee employee, FormatType formatType*/) {
        // EmployeeReportFormatter employeeReportFormatter = new EmployeeReportFormatter(employee, formatType);
        System.out.println(employeeReportFormatter.getFormattedOutputEmployeeValue());
    }
}
