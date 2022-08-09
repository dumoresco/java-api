package br.com.forttiori.mongodb.model.mapper;

import br.com.forttiori.mongodb.model.StudentRequest;
import br.com.forttiori.mongodb.persistence.entity.Students;

import java.time.LocalDateTime;


public class RequestMapper {
    public static Students createEntity(StudentRequest studentRequest) {
    return Students.builder()
        .firstName(studentRequest.getFirstName())
        .lastName(studentRequest.getLastName())
        .age(studentRequest.getAge())
        .email(studentRequest.getEmail())
            .gender(studentRequest.getGender())
        .startDate(LocalDateTime.now())
        .build();
    }

}


