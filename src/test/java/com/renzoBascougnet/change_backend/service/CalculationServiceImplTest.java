package com.renzoBascougnet.change_backend.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.renzoBascougnet.change_backend.service.impl.CalculationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceImplTest {

    @Mock
    private PercentageService percentageService;

    @InjectMocks
    private CalculationServiceImpl calculationService;

    @Test
    void testCalculateWithPercentageFromService() {
        double num1 = 100;
        double num2 = 200;
        double percentage = 10;

        when(percentageService.getPercentage()).thenReturn(percentage);

        double result = calculationService.calculateWithPercentage(num1, num2);

        assertEquals(330, result, 0.001);
        verify(percentageService).getPercentage();
    }
}
