package com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import com.in28minutes.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseSpringDataJpaCommandLineRunner implements CommandLineRunner {

    private CourseSpringDataJpaRepository repository;

    public CourseSpringDataJpaCommandLineRunner(CourseSpringDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        repository.save(new Course(10001,"learn AWS now","Young"));
        repository.save(new Course(10002,"learn React now","Young"));
        repository.save(new Course(10003,"learn DevOps now","Young"));

        repository.save(new Course(10001,"learn AWS now","Jerry"));

        System.out.println(repository.findAll());
        System.out.println(repository.findByName("Young"));
        System.out.println(repository.findById(10002l));

        repository.deleteById(10001l);

        System.out.println(repository.findAll());
    }
}
