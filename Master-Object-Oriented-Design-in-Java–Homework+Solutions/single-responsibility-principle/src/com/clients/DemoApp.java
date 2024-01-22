package com.clients;

import com.clients.dao.EmployeeDao;
import com.clients.domain.Employee;
import com.clients.formatting.EmployeeReportFormatter;
import com.clients.formatting.FormatType;
import com.clients.modules.ClientModule;

public class DemoApp {

    public static void main(String[] args) {

        Employee jack = new Employee(1001L, "jack", "accounting", true);
        ClientModule clientModule = new ClientModule(new EmployeeDao(), new EmployeeReportFormatter(jack, FormatType.CSV));
        clientModule.hireNewEmployee(jack);
        clientModule.printEmployeeReport();
        // clientModule.printEmployeeReport(jack, FormatType.CSV);
        // printEmployeeReport(jack, FormatType.XML);


    }
}
