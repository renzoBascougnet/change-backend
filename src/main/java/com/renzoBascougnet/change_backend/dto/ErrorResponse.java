package com.renzoBascougnet.change_backend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
