package com;

public class SmallEngine implements Engine{

    private int horsePower;

    public SmallEngine(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public void startEngine() {
        System.out.println("start small " + horsePower + " hp engine");

    }
}
