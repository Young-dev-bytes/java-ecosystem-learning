package com.clients.formatting;


public class ReportFormatter {

    String formattedOutput;

    public ReportFormatter(Object object, FormatType formatType) {
        switch (formatType) {
            case CSV:
                formattedOutput = "CSV : <title> " + object.toString() + "</title>";
                break;
            case XML:
                formattedOutput = "XML : <title> " + object.toString() + "</title>";
                break;
        }
    }

    public String getFormattedOutput() {
        return formattedOutput;
    }
}
