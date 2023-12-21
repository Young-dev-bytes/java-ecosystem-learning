package com.in28minutes.learnspringframework.examples.a0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class SimpleSpringContextLauncherApplication {

    public static void main(String[] args) {
        try (var applicationContext =
                     new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)) {
            Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
