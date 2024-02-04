package com.solves;


public class DemoApp {
    public static void main(String[] args) {

        new Vehicle(new LargeEngine(400)).crankIgnition();
        new Vehicle(new SmallEngine(200)).crankIgnition();
    }
}
