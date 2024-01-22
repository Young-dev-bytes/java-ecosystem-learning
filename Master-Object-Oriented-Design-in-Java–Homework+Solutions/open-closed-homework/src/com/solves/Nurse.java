package com.solves;

public class Nurse extends Employee {

    public Nurse(int id, String name, String department, boolean working) {
        super(id, name, department, working);
    }

    @Override
    public void performDuties() {
        checkVitalSigns();
        drawBlood();
        cleanPatientArea();
    }

    // nurses
    public void checkVitalSigns() {
        System.out.println("checkVitalSigns...");
    }

    public void drawBlood() {
        System.out.println("drawing blood...");
    }

    public void cleanPatientArea() {
        System.out.println("cleanPatientArea...");
    }

}
