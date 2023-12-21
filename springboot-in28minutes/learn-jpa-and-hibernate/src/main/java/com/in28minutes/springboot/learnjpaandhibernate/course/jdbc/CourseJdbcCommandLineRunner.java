package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    private CourseJdbcRepository courseJdbcRepository;

    public CourseJdbcCommandLineRunner(CourseJdbcRepository courseJdbcRepository) {
        this.courseJdbcRepository = courseJdbcRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        courseJdbcRepository.insert(new Course(10001,"learn AWS now","Young"));
        courseJdbcRepository.insert(new Course(10002,"learn React now","Young"));
        courseJdbcRepository.insert(new Course(10003,"learn DevOps now","Young"));
        courseJdbcRepository.deleteById(10001);
        System.out.println(courseJdbcRepository.findById(10002));
    }
}
