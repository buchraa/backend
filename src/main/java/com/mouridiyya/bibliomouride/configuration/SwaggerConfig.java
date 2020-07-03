package com.mouridiyya.bibliomouride.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mouridiyya.bibliomouride.controller"))
                .paths(PathSelectors.ant("/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API projet Biblioteque Mouride",
                "Acceder au patrimoine culturel de la Mouridiyya dans le numérique",
                "0.1",
                "Terms of service",
                new Contact("Bibliotech team", "www.mouridiyya.com", "support@mouridiyya.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
