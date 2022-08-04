package br.com.forttiori.mongodb.model.mapper;

import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.Students;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMapper {

    public static StudentResponse createResponse(Students students){
        return StudentResponse.builder()
                .name(students.getName())
                .age(students.getAge())
                .email(students.getEmail())
                .startDate(students.getStartDate())
                .build();

    }

}
