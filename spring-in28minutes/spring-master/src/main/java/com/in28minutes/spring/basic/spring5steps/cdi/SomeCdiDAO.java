package com.in28minutes.spring.basic.spring5steps.cdi;

import org.springframework.stereotype.Repository;


@Repository
public class SomeCdiDAO {

    public int[] getData() {
        return new int[]{5, 89, 100};
    }
}
