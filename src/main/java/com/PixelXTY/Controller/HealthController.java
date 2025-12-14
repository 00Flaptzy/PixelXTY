package com.PixelXTY.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
    
    @GetMapping("/api/auth/health")  // Mantén este también si lo usas
    public String authHealth() {
        return "AUTH OK";
    }
}