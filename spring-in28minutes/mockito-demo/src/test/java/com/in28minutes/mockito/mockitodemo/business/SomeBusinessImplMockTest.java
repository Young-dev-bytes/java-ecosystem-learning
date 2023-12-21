package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplMockTest {

    @InjectMocks
    private SomeBusinessImpl someBusinessImpl;

    @Mock
    private DataService dataServiceMock;

    @Test
    public void testFindTheGreatestFromAllDataBasic() {
        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{12, 34, 11, 33});
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceMock);
        int result = someBusinessImpl.findTheGreatestFromAllData();
        assertEquals(34, result);
    }

    @Test
    public void testFindTheGreatestFromAllDataRefactor() {
        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{12, 34, 11, 33});
        assertEquals(34, new SomeBusinessImpl(dataServiceMock).findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllDataRefactorFinal() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{12, 34, 11, 33});
        assertEquals(34, someBusinessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllData_basicScenario() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{12, 34, 11, 33, 12345});
        assertEquals(12345, someBusinessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllData_oneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{12});
        assertEquals(12, someBusinessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllData_emptyArray() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(Integer.MIN_VALUE, someBusinessImpl.findTheGreatestFromAllData());
    }
}
