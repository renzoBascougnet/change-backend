package com.renzoBascougnet.change_backend.service.impl;

import com.renzoBascougnet.change_backend.dto.CalculateResponse;
import com.renzoBascougnet.change_backend.service.CalculationService;
import com.renzoBascougnet.change_backend.service.PercentageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationServiceImpl implements CalculationService {

    private final PercentageService percentageService;

    @Override
    public CalculateResponse calculateWithPercentage(double num1, double num2) {
        double sum = num1 + num2;
        double percentage = percentageService.getPercentage();
        double result = sum + (sum * (percentage / 100));

        return CalculateResponse.builder().result(result).build();
    }
}
