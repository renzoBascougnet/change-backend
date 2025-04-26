package com.renzoBascougnet.change_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class TestController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db-connection")
    public String testDbConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Conexión exitosa a la base de datos";
        } catch (SQLException e) {
            return "Error al conectar con la base de datos: " + e.getMessage();
        }
    }
}
