package br.com.forttiori.mongodb.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
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
    public Docket swagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.forttiori.mongodb.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfoBuilder()
                .title("Student API")
                .description("API REST for Student info.")
                .version("1.0.0")
                .contact(new Contact("Eduardo Moresco", "https://www.forttiori.it","eduardomorescoiost@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license.html")
                .build();
    }

}
