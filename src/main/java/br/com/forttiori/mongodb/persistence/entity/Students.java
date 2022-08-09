package br.com.forttiori.mongodb.persistence.entity;


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
@Builder
@Document
public class Students {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private LocalDateTime startDate;
    private Gender gender;
    private List<Subjects> subjects;
}
