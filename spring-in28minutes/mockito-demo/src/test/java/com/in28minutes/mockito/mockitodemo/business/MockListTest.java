package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockListTest {

    @Test
    void simpleTest() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(3);
        assertEquals(3, listMock.size());
        assertEquals(3, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    void multipleReturns() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(3).thenReturn(2).thenReturn(1);
        assertEquals(3, listMock.size());
        assertEquals(2, listMock.size());
        assertEquals(1, listMock.size());
    }

    @Test
    void specificParameters() {
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("Young");
        when(listMock.size()).thenReturn(3).thenReturn(2).thenReturn(1);
        assertEquals("Young", listMock.get(0));
        assertEquals(null, listMock.get(1));
        assertEquals(3, listMock.size());
        assertEquals(2, listMock.size());
        assertEquals(1, listMock.size());
    }

    @Test
    void genericParameters() {
        List<String> listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");
        assertEquals("SomeOtherString", listMock.get(0));
        assertEquals("SomeOtherString", listMock.get(1));
        /*System.out.println(Mockito.anyInt());
        System.out.println(Mockito.anyInt());
        System.out.println(Mockito.anyInt());
        System.out.println(listMock.get(0));
        System.out.println(listMock.get(1));
        System.out.println(listMock.get(2));*/
    }
}
