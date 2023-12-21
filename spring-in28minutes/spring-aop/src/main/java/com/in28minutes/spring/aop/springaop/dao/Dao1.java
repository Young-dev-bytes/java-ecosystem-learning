package com.in28minutes.spring.aop.springaop.dao;

import com.in28minutes.spring.aop.springaop.aspect.TrackTime;
import org.springframework.stereotype.Repository;

@Repository
public class Dao1 {

    @TrackTime
    public String retrieveSomething(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "dao1";
    }

}
