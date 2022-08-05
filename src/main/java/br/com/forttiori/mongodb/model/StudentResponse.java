package br.com.forttiori.mongodb.model;

import br.com.forttiori.mongodb.persistence.repository.Subjects;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private String id;
    private String name;
    private Integer age;
    private String email;
    private LocalDateTime startDate;
    private List<Subjects> subjects;
}
