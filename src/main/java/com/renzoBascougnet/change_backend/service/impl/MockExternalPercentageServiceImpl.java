package com.renzoBascougnet.change_backend.service.impl;

import com.renzoBascougnet.change_backend.service.MockExternalPercentageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MockExternalPercentageServiceImpl implements MockExternalPercentageService {
    private int count = 0;

    @Override
    public Double getPercentage() {
        count++;
        log.info("Count: {}", count);

        if (count == 3 || count == 6 || count == 9) {
            log.error("Simulando error en intento {}", count);
            throw new RuntimeException("Error simulado en el intento " + count);
        }

        if (count == 4) {
            log.error("Simulando null en intento {}", count);
            return null;
        }

        return 10.0;
    }
}
