package com.renzoBascougnet.change_backend.service.impl;

import com.renzoBascougnet.change_backend.client.PercentageClient;
import com.renzoBascougnet.change_backend.exception.PercentageNotFoundException;
import com.renzoBascougnet.change_backend.service.PercentageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PercentageServiceImpl implements PercentageService {

    private final PercentageClient percentageClient;
    private final CacheManager cacheManager;

    @Override
    public double getPercentage() {
        try {
            double percentage = percentageClient.getPercentage();
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                cache.put("latest", percentage);
            }
            return percentage;
        } catch (Exception ex) {
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                Double cachedPercentage = cache.get("latest", Double.class);
                if (cachedPercentage != null) {
                    return cachedPercentage;
                }
            }
            throw new PercentageNotFoundException("No se pudo obtener el porcentaje ni hay valor cacheado.");
        }
    }
}
