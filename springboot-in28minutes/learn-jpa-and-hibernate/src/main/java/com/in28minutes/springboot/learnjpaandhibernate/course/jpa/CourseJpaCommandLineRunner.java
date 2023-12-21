package com.in28minutes.springboot.learnjpaandhibernate.course.jpa;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
public class CourseJpaCommandLineRunner implements CommandLineRunner {

    private CourseJpaRepository courseJpaRepository;

    public CourseJpaCommandLineRunner(CourseJpaRepository courseJpaRepository) {
        this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        courseJpaRepository.insert(new Course(10001,"learn AWS now","Young"));
        courseJpaRepository.insert(new Course(10002,"learn React now","Young"));
        courseJpaRepository.insert(new Course(10003,"learn DevOps now","Young"));

        courseJpaRepository.update(new Course(10001,"learn AWS now","Jerry"));

        System.out.println(courseJpaRepository.findAllCourse());
        System.out.println(courseJpaRepository.findAllCourseName());
        System.out.println(courseJpaRepository.findById(10002));

        courseJpaRepository.deleteById(10001);

        System.out.println(courseJpaRepository.findAllCourse());



    }
}
