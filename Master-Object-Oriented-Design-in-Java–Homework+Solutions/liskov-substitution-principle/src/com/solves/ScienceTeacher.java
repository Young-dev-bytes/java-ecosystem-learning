package com.solves;

public class ScienceTeacher extends SchoolStaff implements CourseInstructor {

    @Override
    public void teach() {
        System.out.println("teach science...");
    }
}
