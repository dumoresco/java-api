package br.com.forttiori.mongodb.v1.model.Student.mapper;

import br.com.forttiori.mongodb.v1.model.Student.StudentRequest;
import br.com.forttiori.mongodb.v1.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.v1.persistence.entity.StudentEntity;

import java.time.LocalDateTime;


public class RequestMapper {
    public static StudentEntity createEntity(StudentRequest studentRequest, AddressEntity addressEntity) {
    return StudentEntity.builder()
        .firstName(studentRequest.getFirstName())
        .lastName(studentRequest.getLastName())
        .age(studentRequest.getAge())
        .email(studentRequest.getEmail())
        .document(studentRequest.getDocument())
        .address(getAddress(addressEntity))
        .gender(studentRequest.getGender())
        .startDate(LocalDateTime.now())
        .build();
    }



    private static AddressEntity getAddress(AddressEntity addressEntity) {
        return AddressEntity.builder()
                .cep(addressEntity.getCep())
                .bairro(addressEntity.getBairro())
                .complemento(addressEntity.getComplemento())
                .localidade(addressEntity.getLocalidade())
                .uf(addressEntity.getUf())
                .logradouro(addressEntity.getLogradouro())
                .build();
    }

}


