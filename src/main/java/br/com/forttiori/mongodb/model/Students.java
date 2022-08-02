package br.com.forttiori.mongodb.model;


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Students {

    @Id
    private String id;
    private String name;
    private int age;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private List<Subjects> subjects;
}
