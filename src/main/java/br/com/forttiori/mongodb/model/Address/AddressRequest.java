package br.com.forttiori.mongodb.model.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    public String cep;
    public String logradouro;
    public String complemento;
    public String bairro;
    public String localidade;
    public String uf;
}
