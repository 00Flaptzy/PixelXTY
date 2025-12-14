package com.PixelXTY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PixelXtyApplication {
    public static void main(String[] args) {
        // DEBUG: Mostrar variables
        System.out.println("=== ENVIRONMENT VARIABLES ===");
        System.out.println("PORT: " + System.getenv("PORT"));
        System.out.println("DB_HOST: " + System.getenv("DB_HOST"));
        System.out.println("DB_USER: " + System.getenv("DB_USER"));
        System.out.println("DATABASE_URL: " + System.getenv("DATABASE_URL"));
        System.out.println("=============================");
        
        // Forzar configuración
        System.setProperty("server.address", "0.0.0.0");
        System.setProperty("server.port", "8080");
        
        SpringApplication.run(PixelXtyApplication.class, args);
    }
}