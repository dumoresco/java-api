package br.com.forttiori.mongodb.v1.integration;

import br.com.forttiori.mongodb.v1.persistence.entity.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class AddressIntegration {
    @Autowired
    RestTemplate restTemplate;

    public AddressEntity consultaCep(String cep) {
        var URL = "/ws/" + cep + "/json/";
        return restTemplate.getForObject(URL,  AddressEntity.class);
    }
}
