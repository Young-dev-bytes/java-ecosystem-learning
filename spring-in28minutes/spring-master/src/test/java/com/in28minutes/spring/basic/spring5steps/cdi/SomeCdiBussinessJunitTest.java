package com.in28minutes.spring.basic.spring5steps.cdi;

import com.in28minutes.spring.basic.spring5steps.SpringMasterCdiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringMasterCdiApplication.class)
public class SomeCdiBussinessJunitTest {


    @Autowired
    SomeCdiBussiness someCdiBussiness;

    @Test
    public void testFindGreatest() {
        assertEquals(100, someCdiBussiness.findGreatest());
    }
}
