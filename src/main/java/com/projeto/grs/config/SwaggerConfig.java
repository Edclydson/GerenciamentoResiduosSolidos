package com.projeto.grs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(informacoesDaApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.projeto.grs.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo informacoesDaApi() {
        return new ApiInfoBuilder()
                .title("API Gerenciamento Residuos Solidos")
                .description("Api desenvolvida com base no projeto apresentado no Jovens Talentos do Juventude Digital \n Projeto disponível em: https://github.com/Edclydson")
                .contact(new Contact("Edclydson Sousa","https://linkedin.com/in/edclydson","edclydson.sousa@gmail.com"))
                .version("1.0.0")
                .build();
    }
}
