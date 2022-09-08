package br.com.forttiori.mongodb.util;

import br.com.forttiori.mongodb.v1.model.Student.StudentRequest;
import br.com.forttiori.mongodb.v1.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.v1.persistence.entity.Gender;
import br.com.forttiori.mongodb.v1.persistence.entity.StudentEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentCreator {

    public static StudentEntity studentEntityStub(){
        return StudentEntity.builder()
                .id("1")
                .firstName("Eduardo")
                .lastName("Moresco")
                .age(20)
                .document("87744163087")
                .email("eduardomorescoiost@gmail.com")
                .address(AddressEntity.builder()
                        .cep("91740840")
                        .logradouro("Rua Joaquim de Carvalho 70")
                        .uf("RS")
                        .localidade("Porto Alegre")
                        .complemento("BLoco 1")
                        .bairro("Vila Nova")
                        .build())
                .gender(Gender.MALE)
                .startDate(LocalDateTime.now())
                .build();
    }
    public static StudentEntity studentEntityStub1(){
        return StudentEntity.builder()
                .id("2")
                .firstName("Eduardo 1")
                .lastName("Moresco 1")
                .age(20)
                .document("87744163087")
                .email("eduardomorescoiost@gmail.com")
                .gender(Gender.MALE)
                .startDate(LocalDateTime.now())
                .build();
    }
    public static StudentEntity studentEntityStub2(){
        return StudentEntity.builder()
                .id("3")
                .firstName("Eduardo 2")
                .lastName("Moresco 2")
                .age(20)
                .document("87744163087")
                .email("eduardomorescoiost@gmail.com")
                .gender(Gender.MALE)
                .startDate(LocalDateTime.now())
                .build();
    }

    public static StudentRequest studentRequestStub(){
        return StudentRequest.builder()
                .firstName("Eduardo 2")
                .lastName("Moresco 2")
                .age(20)
                .document("87744163087")
                .email("eduardomorescoiost@gmail.com")
                .gender(Gender.MALE)
                .cep("91740840")
                .startDate(LocalDateTime.now())
                .build();
    }


    public static AddressEntity addressEntityStub(){
        return AddressEntity.builder()
                .cep("91740840")
                .complemento("aaaa")
                .uf("rs")
                .localidade("porto alegre")
                .logradouro("aaaaa")
                .build();
    }
}
