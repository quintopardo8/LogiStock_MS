package com.example.LogiStock_MS_01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("API LOGISTOCK")
                .version("0.1")
                .description("Documentacion de la API para sistema de gestion de stock"));
    }

}
