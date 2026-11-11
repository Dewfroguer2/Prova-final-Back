package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class CorsConfig {
    @Value("${frontend.origin:http://localhost:3000}")
    private String frontendOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/artigos")
                .allowedOrigins(frontendOrigins.split(","))
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type");
    }
}
