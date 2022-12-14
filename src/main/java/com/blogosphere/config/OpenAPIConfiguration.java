package com.blogosphere.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        title = "Blogosphere Web API Reference",
        version = "1.0",
        description = "RESTful web services for the blogosphere app",
        contact = @Contact(
            name = "Sahit Berisha",
            email = "sahitberisha11@gmail.com"
        )))
@SecurityScheme(
    name = "blogosphere",
    scheme = "bearer",
    bearerFormat = "JWT",
    type = SecuritySchemeType.HTTP)
public class OpenAPIConfiguration {

}
