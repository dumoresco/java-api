package br.com.forttiori.mongodb.v1.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //@Builder permite que você produza automaticamente o código necessário para que sua classe seja instanciável com código.
@Document
public class StudentEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Gender gender;
    private String document;
    private AddressEntity address;
    private LocalDateTime startDate;
    private List<Subjects> subjects;
}
