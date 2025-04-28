package com.renzoBascougnet.change_backend.controller;

import com.renzoBascougnet.change_backend.service.MockExternalPercentageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
@Tag(name = "External", description = "Servicios externos relacionados con el cálculo de porcentajes")
public class ExternalPercentageController {

    private final MockExternalPercentageService mockExternalPercentageService;

    @GetMapping("/percentage")
    @Operation(summary = "Obtener porcentaje externo",
            description = "Este endpoint recupera un porcentaje desde un servicio externo simulado. En caso de no encontrar el porcentaje, retorna un estado 204 No Content. Si hay un error o no se puede obtener el porcentaje, el servicio puede retornar una respuesta vacía.")
    public ResponseEntity<Double> getPercentage() {
        Double percentage = mockExternalPercentageService.getPercentage();
        if (percentage == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(percentage);
    }
}
