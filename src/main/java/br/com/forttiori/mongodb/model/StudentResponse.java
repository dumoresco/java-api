package br.com.forttiori.mongodb.model;

import br.com.forttiori.mongodb.persistence.repository.Subjects;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentResponse {

    private String name;
    private Integer age;
    private String email;
    private LocalDateTime startDate;
    private List<Subjects> subjects;
}
