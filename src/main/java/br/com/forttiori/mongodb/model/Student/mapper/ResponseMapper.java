package br.com.forttiori.mongodb.model.Student.mapper;

import br.com.forttiori.mongodb.model.Student.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentEntity;
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
            .subjects(students.getSubjects())
        .build();
    }

}
