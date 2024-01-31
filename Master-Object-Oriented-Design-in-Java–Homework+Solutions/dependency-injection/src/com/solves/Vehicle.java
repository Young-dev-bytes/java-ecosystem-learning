package com.solves;

public class Vehicle {

    Engine racingEngine;

    public Vehicle(Engine racingEngine) {
        this.racingEngine = racingEngine;
    }

    public void crankIgnition() {
        racingEngine.startEngine();
        System.out.println("Vehicle is running...");
    }
}
