package com;

public class DemoApp {

    public static void main(String[] args) {

        User build = new User.Builder("Young", "Young@gmail.com")
                .firstName("Chen")
                .lastName("Young")
                .phoneNumber(1888888)
                .address("China")
                .build();
        // User.Builder young = User.getBuild("Young", "Young@gmail.com");

        System.out.println(build);

        Vehicle build1 = new Vehicle.Builder().build();

    }
}
