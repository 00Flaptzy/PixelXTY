package com.PixelXTY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PixelXtyApplication {

    public static void main(String[] args) {
        // Configurar puerto para Render
        String port = System.getenv("PORT");
        if (port != null) {
            System.setProperty("server.port", port);
        }
        // Asegurar que escucha en todas las interfaces (necesario para Render)
        System.setProperty("server.address", "0.0.0.0");
        
        SpringApplication.run(PixelXtyApplication.class, args);
    }
}