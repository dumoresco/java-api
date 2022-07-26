package br.com.forttiori.mongodb.v1.integration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate cepRestTemplate(){
    // .errorHandler()
        return new RestTemplateBuilder().rootUri("https://viacep.com.br").build();

    }

}
