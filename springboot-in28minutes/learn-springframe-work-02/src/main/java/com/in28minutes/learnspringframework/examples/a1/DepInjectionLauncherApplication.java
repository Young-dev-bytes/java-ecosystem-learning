package com.in28minutes.learnspringframework.examples.a1;

import com.in28minutes.learnspringframework.game.GameRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class DepInjectionLauncherApplication {

    public static void main(String[] args) {
        try (var applicationContext =
                     new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)) {
            Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
