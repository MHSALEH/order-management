package com.example.ordermanagement.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private Info apiInfo(){
        return new Info().title("order - management").description("E-Commerce Application ").version("1")
                .contact(new Contact().name("Mohammad Haythem")
                        .url( "www.haytham-Tecnologies.ps")
                        .email( "mohd.saleh1191@gmail.com"));
    }

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(AUTHORIZATION_HEADER,
                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name(AUTHORIZATION_HEADER)
                                        .bearerFormat("JWT")))

                .info(apiInfo())
                .addSecurityItem(
                        new SecurityRequirement().addList(AUTHORIZATION_HEADER, Collections.singletonList("global")));

    }

}
