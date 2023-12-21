package com.in28minutes.learnspringframework.helloworld;

import com.in28minutes.learnspringframework.entity.Address;
import com.in28minutes.learnspringframework.entity.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
// @ComponentScan(value = "com")
// @SpringBootApplication
public class HelloWorldConfiguration {

    @Bean
    public String name() {
        return "Young";
    }

    @Bean
    public int age() {
        return 16;
    }

    @Bean
    public Person person() {
        return new Person("jack", 12, new Address("Private Street", "Paris"));
    }

    @Bean
    public Person person2MethodCall() {
        return new Person(name(), age(), address());
    }

    @Bean
    public Person person3Parameters(String name, int age,  Address address3) {
        return new Person(name, age, address3);
    }

    @Bean
    @Primary
    public Person person4Parameters(String name, int age,  Address address) {
        return new Person(name, age, address);
    }

    @Bean
    public Person person5Qualifier(String name, int age,  @Qualifier("address3qualifier") Address address) {
        return new Person(name, age, address);
    }


    @Bean(name = "address2")
    // @Qualifier(value = "address2")
    @Primary
    public Address address() {
        return new Address("Baker Street", "London");
    }

    @Bean(name = "address3")
    @Qualifier(value = "address3qualifier")
    // @Primary
    public Address address3() {
        return new Address("Motinagar", "Hyderabad");
    }
}
