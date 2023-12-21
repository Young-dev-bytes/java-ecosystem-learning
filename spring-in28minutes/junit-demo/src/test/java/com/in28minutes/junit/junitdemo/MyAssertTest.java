package com.in28minutes.junit.junitdemo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MyAssertTest {

    List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");


    @Test
    void testAssertMethods() {

        boolean isHasAws = todos.contains("AWS");
        boolean isHasAwt = todos.contains("AWT");
        assertTrue(isHasAws);
        assertFalse(isHasAwt);
        assertEquals(true, isHasAws);
        assertEquals(3, todos.size());
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        assertNotNull(todos);
    }
}
