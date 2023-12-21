package com.in28minutes.spring.aop.springaop.business;

import com.in28minutes.spring.aop.springaop.aspect.TrackTime;
import com.in28minutes.spring.aop.springaop.dao.Dao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business1 {

    @Autowired
    private Dao1 dao1;

    @TrackTime
    public String calculateSomething(){
        //Business Logic
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dao1.retrieveSomething();
    }
}
