package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime localDateTime;

    private String message;

    private String details;

    public ErrorDetails(LocalDateTime localDateTime, String message, String details) {
        this.localDateTime = localDateTime;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
