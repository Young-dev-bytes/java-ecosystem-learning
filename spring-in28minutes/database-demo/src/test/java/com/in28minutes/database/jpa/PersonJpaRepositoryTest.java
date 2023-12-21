package com.in28minutes.database.jpa;

import com.in28minutes.database.DatabaseDemoApplicationTests;
import com.in28minutes.database.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonJpaRepositoryTest extends DatabaseDemoApplicationTests {

    @Autowired
    PersonJpaRepository personJpaRepository;

    @Test
    public void findById() {
        Person person = personJpaRepository.findById(10001);
        System.out.println(person);
    }
}
