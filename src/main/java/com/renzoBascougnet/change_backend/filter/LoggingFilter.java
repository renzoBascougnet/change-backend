package com.renzoBascougnet.change_backend.filter;

import com.renzoBascougnet.change_backend.entity.RequestLog;
import com.renzoBascougnet.change_backend.service.RequestLogService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    private final RequestLogService requestLogService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        log.info("doFilterInternal");
        String endpoint = request.getRequestURI();
        String parameters = request.getQueryString() != null ? request.getQueryString() : "N/A";

        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(request, wrappedResponse);
        } catch (Exception e) {
            if(!"/external/percentage".equals(endpoint)){
                logRequest(endpoint, parameters, e.getMessage(), true);
            }
        }

        String responseBody = new String(wrappedResponse.getContentAsByteArray(), wrappedResponse.getCharacterEncoding());

        log.info(responseBody);

        HttpStatus status = HttpStatus.valueOf(wrappedResponse.getStatus());
        boolean isError = !status.is2xxSuccessful();

        if(!"/external/percentage".equals(endpoint)) {
            logRequest(endpoint, parameters, responseBody, isError);
        }

        wrappedResponse.copyBodyToResponse();
    }

    private void logRequest(String endpoint, String parameters, String response, boolean error) {
        requestLogService.saveRequestLog(RequestLog.builder()
                .timestamp(LocalDateTime.now())
                .endpoint(endpoint)
                .parameters(parameters)
                .response(response)
                .error(error)
                .build());
    }

}
