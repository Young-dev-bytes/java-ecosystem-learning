package com.solves;

public class EmergencyRoomProcess {
    public static void main(String[] args) {

        HospitalManagement hospitalManagement = new HospitalManagement();
        hospitalManagement.callUpon(new Nurse(1,"jerry","emergency",true));
        hospitalManagement.callUpon(new Doctor(2,"tom","emergency",true));

    }
}
