package com.renzoBascougnet.change_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RequestLogResponse {
    private LocalDateTime timestamp;
    private String endpoint;
    private String parameters;
    private String response;
    private boolean error;
}
