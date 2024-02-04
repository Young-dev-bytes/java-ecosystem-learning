package com.solves;

public class SchoolStaff {

    private void makeAnnouncements(){
        System.out.println("made announcements...");
    }

    private void takeAttendence(){
        System.out.println("took attendence...");
    }

    private void collectPaperWork(){
        System.out.println("collect paper work...");
    }

    private void conductHallwayDuties(){
        System.out.println("conduct hallway duties...");
    }

    public void performOtherResponsibilities(){
        makeAnnouncements();
        takeAttendence();
        collectPaperWork();
        conductHallwayDuties();
    }
}
