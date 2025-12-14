package com.PixelXTY.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class WebConfig {
    
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        try {
            factory.setAddress(InetAddress.getByName("0.0.0.0"));
        } catch (UnknownHostException e) {
            // Si falla, al menos asegurar que no use solo localhost
            System.err.println("Error setting address to 0.0.0.0: " + e.getMessage());
        }
        return factory;
    }
}