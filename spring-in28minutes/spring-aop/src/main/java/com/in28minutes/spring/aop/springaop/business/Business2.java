package com.in28minutes.spring.aop.springaop.business;

import com.in28minutes.spring.aop.springaop.dao.Dao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class Business2 {

    @Autowired
    private Dao2 dao2;

    public String calculateSomething(){
        //Business Logic
        String retrieveValue= dao2.retrieveSomething();
        if(Objects.isNull(retrieveValue)) {
            throw new RuntimeException("the value of retrieveSomething is null!");
        }
        return retrieveValue;
    }
}
