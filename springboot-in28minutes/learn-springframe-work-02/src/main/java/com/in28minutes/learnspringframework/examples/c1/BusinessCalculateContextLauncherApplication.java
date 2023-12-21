package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class BusinessCalculateContextLauncherApplication {

    public static void main(String[] args) {
        try (var applicationContext =
                     new AnnotationConfigApplicationContext(BusinessCalculateContextLauncherApplication.class)) {
            Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

            int max = applicationContext.getBean(BusinessCalculateService.class).findMax();
            System.out.println(max);
        }
    }
}
