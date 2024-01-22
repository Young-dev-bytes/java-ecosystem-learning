package com.clients.formatting;

public class EmployeeReportFormatter extends ReportFormatter {


    public EmployeeReportFormatter(Object object, FormatType formatType) {
        super(object, formatType);
    }

    public String getFormattedOutputEmployeeValue(){
        return getFormattedOutput();
    }

}
