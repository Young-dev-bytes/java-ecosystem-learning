package com.in28minutes.database;

import com.in28minutes.database.entity.Person;
import com.in28minutes.database.jpa.PersonJpaRepository;
import com.in28minutes.database.springdata.PersonSpringDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonSpringDataRepository personSpringDataRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("User id 10001 -> {}", personSpringDataRepository.findById(10001));
        logger.info("User id 10002 -> {}", personSpringDataRepository.findById(10002));

        logger.info("new user 10005 -> {}", personSpringDataRepository.save(new Person(10005, "Young", "China", new Date())));
        logger.info("new user 10004 -> {}", personSpringDataRepository.save(new Person(10004, "Luck", "Japan", new Date())));

        logger.info("update user 10005 -> {}", personSpringDataRepository.save(new Person(10005, "Mike", "USA", new Date())));
        personSpringDataRepository.deleteById(10001);

        logger.info("all user -> {}", personSpringDataRepository.findAll());
    }
}
