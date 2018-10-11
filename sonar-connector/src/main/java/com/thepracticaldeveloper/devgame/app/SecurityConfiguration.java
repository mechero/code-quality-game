package com.thepracticaldeveloper.devgame.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // Modifies CORS configuration to allow connecting from the Angular frontend.
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("OPTIONS", "POST", "PUT", "GET", "DELETE", "PATCH")
                        .allowedOrigins("*");
            }
        };
    }

}
