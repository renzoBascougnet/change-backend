package com.renzoBascougnet.change_backend.controller;

import com.renzoBascougnet.change_backend.entity.RequestLog;
import com.renzoBascougnet.change_backend.service.RequestLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request-history")
@RequiredArgsConstructor
@Slf4j
public class RequestLogController {

    private final RequestLogService requestLogService;

    @GetMapping
    public ResponseEntity<Page<RequestLog>> getRequestHistory(@RequestParam int page, @RequestParam int size) {
        Page<RequestLog> requestLogs = requestLogService.findAll(page, size);
        requestLogs.getContent().forEach(requestLog -> log.info(requestLogs.toString()));
        return ResponseEntity.ok(requestLogs);
    }
}
