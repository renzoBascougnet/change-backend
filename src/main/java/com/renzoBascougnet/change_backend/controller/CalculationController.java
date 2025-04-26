package com.renzoBascougnet.change_backend.controller;

import com.renzoBascougnet.change_backend.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/calculation")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @PostMapping
    public ResponseEntity<?> calculate(@RequestParam double num1, @RequestParam double num2){
        try{
            double result = calculationService.calculateWithPercentage(num1, num2);
            return ResponseEntity.ok(result);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
