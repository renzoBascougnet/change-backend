package com.renzoBascougnet.change_backend.service.impl;

import com.renzoBascougnet.change_backend.client.PercentageClient;
import com.renzoBascougnet.change_backend.exception.PercentageNotFoundException;
import com.renzoBascougnet.change_backend.service.PercentageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PercentageServiceImpl implements PercentageService {

    private final PercentageClient percentageClient;
    private final CacheManager cacheManager;

    @Override
    public Double getPercentage() {
        try {
            Double percentage = percentageClient.getPercentage();
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                cache.put("latest", percentage);
            }
            log.info("porcentaje: "+percentage);
            return percentage;
        } catch (Exception ex) {

            log.error("fallo el feign");
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                Double cachedPercentage = cache.get("latest", Double.class);
                if (cachedPercentage != null) {

                    log.info("porcentaje cacheado: " + cachedPercentage);
                    return cachedPercentage;
                }
            }
            throw new PercentageNotFoundException("No se pudo obtener el porcentaje ni hay valor cacheado.");
        }
    }
}
