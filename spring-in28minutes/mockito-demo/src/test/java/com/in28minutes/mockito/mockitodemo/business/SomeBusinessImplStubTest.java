package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplStubTest {


    @Test
    public void testFindTheGreatestFromAllData() {
        int result = new SomeBusinessImpl(new DataService() {
            @Override
            public int[] retrieveAllData() {
                return new int[]{3, 23, 14, 56};
            }
        }).findTheGreatestFromAllData();
        assertEquals(56, result);
    }
}
