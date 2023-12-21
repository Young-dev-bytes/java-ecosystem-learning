package com.in28minutes.database;

import com.in28minutes.database.entity.Person;
import com.in28minutes.database.jdbc.PersonJbdcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

// @SpringBootApplication
public class JdbcDemoApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonJbdcDao personJbdcDao;

    public static void main(String[] args) {
        SpringApplication.run(JdbcDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*logger.info("one user -> 10001:{}",personJbdcDao.findById(10001));
        logger.info("delete user -> 10001:{}",personJbdcDao.deleteById(10001));
        logger.info("All users -> {}", personJbdcDao.findAll());*/

        logger.info("All users -> {}", personJbdcDao.findAll());

        logger.info("User id 10001 -> {}", personJbdcDao.findById(10001));

        logger.info("Deleting 10002 -> No of Rows Deleted - {}",
                personJbdcDao.deleteById(10002));

        logger.info("Inserting 10004 -> {}",
                personJbdcDao.insert(new Person(10004, "Tara", "Berlin", new Date())));

        logger.info("Update 10003 -> {}",
                personJbdcDao.update(new Person(1000, "Pieter", "Utrecht", new Date())));


    }
}
