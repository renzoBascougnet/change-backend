package com.renzoBascougnet.change_backend.controller;

import com.renzoBascougnet.change_backend.entity.RequestLog;
import com.renzoBascougnet.change_backend.service.RequestLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request-history")
@RequiredArgsConstructor
@Tag(name = "Request Logs", description = "Historial de solicitudes realizadas a la API")
public class RequestLogController {

    private final RequestLogService requestLogService;

    @GetMapping
    @Operation(summary = "Obtener historial de requests", description = "Devuelve un historial paginado de todas las solicitudes realizadas")
    public ResponseEntity<Page<RequestLog>> getRequestHistory(
            @Parameter(description = "Número de página", example = "0") @RequestParam int page,
            @Parameter(description = "Tamaño de página", example = "5") @RequestParam int size) {
        Page<RequestLog> requestLogs = requestLogService.findAll(page, size);
        return ResponseEntity.ok(requestLogs);
    }
}
