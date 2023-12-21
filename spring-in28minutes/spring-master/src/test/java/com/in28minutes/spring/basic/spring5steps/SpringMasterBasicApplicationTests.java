package com.in28minutes.spring.basic.spring5steps;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringMasterBasicApplication.class)
public class SpringMasterBasicApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("contextLoads");
    }
}
