package com.renzoBascougnet.change_backend.service;

import com.renzoBascougnet.change_backend.dto.CalculateResponse;

public interface CalculationService {
    CalculateResponse calculateWithPercentage(double num1, double num2);
}
