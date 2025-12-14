package com.PixelXTY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PixelXtyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PixelXtyApplication.class);
    }

    public static void main(String[] args) {
        // Configuración explícita para Render
        SpringApplication app = new SpringApplication(PixelXtyApplication.class);
        
        // Forzar que escuche en 0.0.0.0
        System.setProperty("server.address", "0.0.0.0");
        
        // Manejar puerto de Render
        String port = System.getenv("PORT");
        if (port != null) {
            System.setProperty("server.port", port);
        } else {
            System.setProperty("server.port", "8080");
        }
        
        app.run(args);
    }
}