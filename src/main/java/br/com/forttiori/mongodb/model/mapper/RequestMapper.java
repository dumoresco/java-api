package br.com.forttiori.mongodb.model.mapper;

import br.com.forttiori.mongodb.model.StudentRequest;
import br.com.forttiori.mongodb.persistence.entity.Students;

import java.time.LocalDateTime;


public class RequestMapper {
    public static Students createEntity(StudentRequest studentRequest) {
        return Students.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .startDate(LocalDateTime.now())
                .build();
    }

}


