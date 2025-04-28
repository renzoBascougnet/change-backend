package com.renzoBascougnet.change_backend.controller;

import com.renzoBascougnet.change_backend.dto.CalculateResponse;
import com.renzoBascougnet.change_backend.service.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/calculation")
@RequiredArgsConstructor
@Tag(name = "Calculations", description = "Operaciones para realizar cálculos.")
public class CalculationController {

    private final CalculationService calculationService;

    @GetMapping
    @Operation(summary = "Calcular porcentaje", description = "Realiza un cálculo de suma entre dos números (num1, num2) y le aplica un porcentaje.")
    public ResponseEntity<CalculateResponse> calculate(
            @Parameter(description = "Primer número para el cálculo", example = "100") @RequestParam double num1,
            @Parameter(description = "Segundo número para el cálculo", example = "100") @RequestParam double num2){
        double result = calculationService.calculateWithPercentage(num1, num2);
        CalculateResponse response = CalculateResponse.builder().result(result).build();
        return ResponseEntity.ok(response);
    }
}
