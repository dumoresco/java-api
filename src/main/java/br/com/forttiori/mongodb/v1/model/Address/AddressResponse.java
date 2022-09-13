package br.com.forttiori.mongodb.v1.model.Address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
