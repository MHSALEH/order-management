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
    // Information about the API
    private Info apiInfo(){
        return new Info().title("order - management").description("E-Commerce Application ").version("1")
                .contact(new Contact().name("Mohammad Haythem")
                        .url( "www.haytham-Tecnologies.ps")
                        .email( "mohd.saleh1191@gmail.com"));
    }

    // Bean that configures the OpenAPI documentation generator
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .components(
                        // Add the Authorization header as a recognized security scheme
                        new Components().addSecuritySchemes(AUTHORIZATION_HEADER,
                                // The type of the security scheme
                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                        // Where the security data is located
                                        .in(SecurityScheme.In.HEADER)
                                        // The name of the header
                                        .name(AUTHORIZATION_HEADER)
                                        // The format of the bearer token
                                        .bearerFormat("JWT")))

                .info(apiInfo())

                // Add a global security requirement that applies to all operations
                .addSecurityItem(
                        new SecurityRequirement().addList(AUTHORIZATION_HEADER, Collections.singletonList("global")));

    }

}
