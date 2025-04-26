package com.renzoBascougnet.change_backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExternalPercentageController {

    @GetMapping("/external/percentage")
    public Double getPercentage() {
        log.info("/external/percentage");
        return 50.0;
    }
}
