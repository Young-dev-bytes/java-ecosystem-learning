package com.problems;

public class Vehicle {

    Engine racingEngine = new LargeEngine(500);

    public void crankIgnition() {
        racingEngine.startEngine();
        System.out.println("Vehicle is running...");
    }
}
