package br.com.forttiori.mongodb.v1.model.Student.mapper;

import br.com.forttiori.mongodb.v1.model.Student.StudentResponse;
import br.com.forttiori.mongodb.v1.persistence.entity.StudentEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMapper {

    public static StudentResponse createResponse(StudentEntity students){
    return StudentResponse.builder()
        .id(students.getId())
        .firstName(students.getFirstName())
        .lastName(students.getLastName())
        .age(students.getAge())
        .email(students.getEmail())
            .gender(students.getGender())
            .document(students.getDocument())
            .address(students.getAddress())
        .startDate(students.getStartDate())
        .build();
    }

}
