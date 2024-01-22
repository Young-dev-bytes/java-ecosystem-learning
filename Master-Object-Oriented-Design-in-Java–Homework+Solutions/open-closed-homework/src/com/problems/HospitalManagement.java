package com.problems;

public class HospitalManagement {


    public void callUpon(Employee employee){
        if(employee instanceof Nurse) {
            checkVitalSigns();
            drawBlood();
            cleanPatientArea();
        }else if(employee instanceof Doctor) {
            prescribeMedication();
            diagnosePatients();
        }
    }

    // nurses
    private void checkVitalSigns() {
        System.out.println("checkVitalSigns...");
    }

    private void drawBlood() {
        System.out.println("drawing blood...");
    }

    private void cleanPatientArea() {
        System.out.println("cleanPatientArea...");
    }

    // doctors
    private void prescribeMedication() {
        System.out.println("prescribeMedication...");
    }

    private void diagnosePatients() {
        System.out.println("diagnosing patient...");
    }
}
