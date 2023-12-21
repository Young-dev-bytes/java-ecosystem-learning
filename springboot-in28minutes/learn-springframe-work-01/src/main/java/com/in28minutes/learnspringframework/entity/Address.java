package com.in28minutes.learnspringframework.entity;

public class Address {

    private String firstLine;

    private String  country;

    public Address(String firstLine, String country) {
        this.firstLine = firstLine;
        this.country = country;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "firstLine='" + firstLine + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
