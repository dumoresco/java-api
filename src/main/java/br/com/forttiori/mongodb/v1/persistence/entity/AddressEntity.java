package br.com.forttiori.mongodb.v1.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

        @NotNull
        public String cep;
        public String logradouro;
        public String complemento;
        public String bairro;
        public String localidade;
        public String uf;
}
