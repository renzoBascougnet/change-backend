package com.renzoBascougnet.change_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cálculo y Registro de Historial")
                        .version("1.0.0")
                        .description("Servicio REST que permite realizar cálculos aplicando un porcentaje dinámico obtenido de un servicio externo (mockeado), almacenar en caché este porcentaje, y registrar el historial de llamadas realizadas a la API en una base de datos PostgreSQL.")
                        .contact(new Contact()
                                .name("Renzo Bascougnet")
                                .email("renzo.bascougnet@gmail.com")
                                .url("https://www.linkedin.com/in/renzobascougnet/")
                        )
                );
    }
}
