package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
        .title("VIRTUAL HEALTHCARE MANAGEMENT")
        .version("1.0")
        .description("API documentation for the VIRTUAL HEALTHCARE MANAGEMENT Platform")
        .contact(new Contact()
        .name("Support Team")
        .email("support@inventory.com")
        .url("https://smartinventory.com"))
        .license(new License().name("Apache 2.0")
        .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}