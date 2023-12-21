package com.in28minutes.spring.basic.spring5steps.basic;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/testContext.xml")
public class BinarySearchXMLConfigurationTest {

    @Autowired
    private BinarySearchImpl binarySearch;

    @Test
    public void testBasicScenario() {
        assertEquals(3, binarySearch.binarySearch(new int[]{12, 23}, 12));
    }
}
