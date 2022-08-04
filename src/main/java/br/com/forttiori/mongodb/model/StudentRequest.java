package br.com.forttiori.mongodb.model;

import br.com.forttiori.mongodb.persistence.repository.Subjects;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentRequest {

    @Size(min = 3, message = "O nome precisa ter no mínimo {min} caracteres.")
    @NotBlank(message = "Name cannot be empty or null.")
    private String name;

    @Positive
    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotBlank(message = "Student e-mail cannot be empty or null.")
    @Email(message = "Student email should be valid")
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime startDate;

    private List<Subjects> subjects;

}
