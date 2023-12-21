package com.in28minutes.learnspringframework.helloworld;


import com.in28minutes.learnspringframework.entity.Address;
import com.in28minutes.learnspringframework.entity.Person;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {

    public static void main(String[] args) {

        // launch a spring context
        try (var applicationContext =
                     new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);) {
            String[] definitionNames = applicationContext.getBeanDefinitionNames();
            Arrays.stream(definitionNames).forEach(System.out::println);

            // System.out.println(applicationContext.getBean("helloWorldConfiguration"));
            System.out.println(applicationContext.getBean("name"));
            System.out.println(applicationContext.getBean("age"));
            System.out.println(applicationContext.getBean("person"));
            System.out.println(applicationContext.getBean("person2MethodCall"));
            System.out.println(applicationContext.getBean("person3Parameters"));
            System.out.println(applicationContext.getBean(Person.class));
            System.out.println(applicationContext.getBean("person5Qualifier"));
            System.out.println(applicationContext.getBean("address2"));
            System.out.println(applicationContext.getBean(Address.class));

        }
    }

}
