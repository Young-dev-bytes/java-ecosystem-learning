package com.in28minutes.springboot.learnspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LearnSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringbootApplication.class, args);

        /*try (var applicationContext = new AnnotationConfigApplicationContext(LearnSpringbootApplication.class)){
            // CurrencyProperties currencyProperties = applicationContext.getBean(CurrencyProperties.class);
            // System.out.println(currencyProperties.url);
            CurrencyServiceConfiguration currencyServiceConfiguration =
                    applicationContext.getBean(CurrencyServiceConfiguration.class);
            System.out.println(currencyServiceConfiguration);


        }*/
    }

}
