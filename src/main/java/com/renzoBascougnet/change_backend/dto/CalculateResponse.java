package com.renzoBascougnet.change_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculateResponse {
    private Double result;
}
