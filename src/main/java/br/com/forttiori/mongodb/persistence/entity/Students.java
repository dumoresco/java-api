package br.com.forttiori.mongodb.persistence.entity;


import br.com.forttiori.mongodb.persistence.repository.Subjects;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Students {

    @Id
    private String id;


    @Size(min = 3, message = "O nome precisa ter no mínimo {min} caracteres.")
    @NotBlank(message = "Name cannot be empty or null.")
    private String name;


    @Positive
    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotBlank(message = "Student e-mail cannot be empty or null.")
    @Email(message = "Student email should be valid")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    private List<Subjects> subjects;
}
