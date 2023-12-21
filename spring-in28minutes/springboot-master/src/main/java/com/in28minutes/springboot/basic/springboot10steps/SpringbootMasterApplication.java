package com.in28minutes.springboot.basic.springboot10steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringbootMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMasterApplication.class, args);
        /*CourseController courseController = applicationContext.getBean(CourseController.class);
        System.out.println(courseController);*/
        /*ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringbootMasterApplication.class);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }*/
    }
}
