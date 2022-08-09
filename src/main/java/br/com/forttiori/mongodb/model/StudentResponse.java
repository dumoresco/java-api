package br.com.forttiori.mongodb.model;

import br.com.forttiori.mongodb.persistence.entity.Gender;
import br.com.forttiori.mongodb.persistence.entity.Subjects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Gender gender;
    private LocalDateTime startDate;
    private List<Subjects> subjects;
}
