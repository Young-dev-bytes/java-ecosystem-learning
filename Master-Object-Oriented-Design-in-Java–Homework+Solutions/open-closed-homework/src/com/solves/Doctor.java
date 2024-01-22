package com.solves;

public class Doctor extends Employee {

    public Doctor(int id, String name, String department, boolean working) {
        super(id, name, department, working);
    }

    @Override
    public void performDuties() {
        prescribeMedication();
        diagnosePatients();
    }

    // doctors
    public void prescribeMedication() {
        System.out.println("prescribeMedication...");
    }

    public void diagnosePatients() {
        System.out.println("diagnosing patient...");
    }
}
