package com.renzoBascougnet.change_backend.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.renzoBascougnet.change_backend.client.PercentageClient;
import com.renzoBascougnet.change_backend.exception.PercentageNotFoundException;
import com.renzoBascougnet.change_backend.service.impl.PercentageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@ExtendWith(MockitoExtension.class)
public class PercentageServiceImplTest {

    @Mock
    private PercentageClient percentageClient;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @InjectMocks
    private PercentageServiceImpl percentageService;

    @Test
    void testGetPercentageAndCacheIt() {
        double expectedPercentage = 15.0;

        when(percentageClient.getPercentage()).thenReturn(expectedPercentage);
        when(cacheManager.getCache("percentage")).thenReturn(cache);

        double result = percentageService.getPercentage();

        assertEquals(expectedPercentage, result);
        verify(cache).put("latest", expectedPercentage);
    }

    @Test
    void testGetPercentageFromCacheWhenServiceFails() {
        double cachedPercentage = 12.0;

        when(percentageClient.getPercentage()).thenThrow(new RuntimeException("Service down"));
        when(cacheManager.getCache("percentage")).thenReturn(cache);
        when(cache.get("latest", Double.class)).thenReturn(cachedPercentage);

        double result = percentageService.getPercentage();

        assertEquals(cachedPercentage, result);
    }

    @Test
    void testThrowsExceptionWhenNoServiceAndNoCache() {
        when(percentageClient.getPercentage()).thenThrow(new RuntimeException("Service down"));
        when(cacheManager.getCache("percentage")).thenReturn(cache);
        when(cache.get("latest", Double.class)).thenReturn(null);

        assertThrows(PercentageNotFoundException.class, () -> percentageService.getPercentage());
    }
}
