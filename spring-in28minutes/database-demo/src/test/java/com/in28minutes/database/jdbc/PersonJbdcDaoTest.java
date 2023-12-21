package com.in28minutes.database.jdbc;


import com.in28minutes.database.DatabaseDemoApplicationTests;
import com.in28minutes.database.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonJbdcDaoTest extends DatabaseDemoApplicationTests {

    @Autowired
    private PersonJbdcDao personJbdcDao;

    @Test
    public void test_findAll() {
        List<Person> all = personJbdcDao.findAll_();
        System.out.println(all);
    }
}
