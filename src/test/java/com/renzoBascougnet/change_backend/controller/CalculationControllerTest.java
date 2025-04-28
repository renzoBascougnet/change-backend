package com.renzoBascougnet.change_backend.controller;

import com.renzoBascougnet.change_backend.service.CalculationService;
import com.renzoBascougnet.change_backend.service.RequestLogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CalculationController.class)
public class CalculationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculationService calculationService;

    @MockBean
    private RequestLogService requestLogService;

    @Test
    void calculate_ReturnsOk() throws Exception {
        double num1 = 100;
        double num2 = 200;
        double expectedResult = 345.0;

        Mockito.when(calculationService.calculateWithPercentage(num1, num2))
                .thenReturn(expectedResult);

        mockMvc.perform(get("/api/calculation")
                        .param("num1", String.valueOf(num1))
                        .param("num2", String.valueOf(num2)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\":345.0}"));

        Mockito.verify(calculationService).calculateWithPercentage(num1, num2);
    }
}
