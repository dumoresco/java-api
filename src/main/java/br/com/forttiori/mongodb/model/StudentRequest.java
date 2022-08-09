package br.com.forttiori.mongodb.model;

import br.com.forttiori.mongodb.persistence.repository.Subjects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentRequest {


    @Size(min = 3, message = "Name must have at least {min} letters")
    @NotBlank(message = "Name cannot be empty or null.")
    private String name;

    @Positive(message = "Age must be greater than 0")
    @NotNull(message = "Age cannot be null")
    private Integer age;

  @Email(
      message = "Student email should be valid",
      regexp =
              "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
  @NotBlank(message = "Student e-mail cannot be empty or null.")
  private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime startDate;

    private List<Subjects> subjects;

}
