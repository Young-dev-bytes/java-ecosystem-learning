package com.in28minutes.spring.basic.spring5steps.cdi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SomeCdiBussinessMockTest {

    @Mock
    SomeCdiDAO someCdiDAO;

    @InjectMocks
    SomeCdiBussiness someCdiBussiness;

    @Test
    public void testFindGreatestBasic_NoAnnotation() {
        SomeCdiDAO someCdiDAOMock = mock(SomeCdiDAO.class);
        // System.out.println(Arrays.toString(someCdiDAOMock.getData()));
        when(someCdiDAOMock.getData()).thenReturn(new int[]{1000, 1001, 1002});
        SomeCdiBussiness someCdiBussiness = new SomeCdiBussiness();
        someCdiBussiness.setSomeCdiDAO(someCdiDAOMock);
        assertEquals(1002, someCdiBussiness.findGreatest());
    }

    @Test
    public void testFindGreatestRefactor_YesAnnotation() {
        when(someCdiDAO.getData()).thenReturn(new int[]{100, 101, 102});
        assertEquals(102, someCdiBussiness.findGreatest());
    }
}
