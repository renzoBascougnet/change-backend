package com.renzoBascougnet.change_backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExternalPercentageController {
    private int count = 0;
    @GetMapping("/external/percentage")
    public ResponseEntity<Double> getPercentage() {
        count++;

        log.info("/external/percentage");
        log.info("count: " + count);

        if (count == 3 || count == 6 || count == 9) {
            log.error("Simulando error en intento " + count);
            throw new RuntimeException("Error simulado en el intento " + count);
        }

        return ResponseEntity.ok(20.0);
    }
}
