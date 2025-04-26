package com.renzoBascougnet.change_backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "percentageClient", url = "http://localhost:8080")
public interface PercentageClient {

    @GetMapping("/external/percentage")
    Double getPercentage();
}
