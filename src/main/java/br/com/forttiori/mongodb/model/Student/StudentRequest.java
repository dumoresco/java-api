package br.com.forttiori.mongodb.model.Student;

import br.com.forttiori.mongodb.persistence.entity.Gender;
import br.com.forttiori.mongodb.persistence.entity.Subjects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

  @Size(min = 3, message = "First Name must have at least {min} letters")
  @NotBlank(message = "First Name cannot be empty or null.")
  private String firstName;
  @Size(min = 3, message = "Last name must have at least {min} letters")
  @NotBlank(message = "Last Name cannot be empty or null.")
  private String lastName;
  @Positive(message = "Age must be greater than 0")
  @NotNull(message = "Age cannot be null")
  private Integer age;
  @Email(
      message = "StudentEntity email should be valid",
      regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
  @NotBlank(message = "StudentEntity e-mail cannot be empty or null.")
  private String email;
  @JsonProperty() private Gender gender;
  @NotBlank(message = "StudentEntity cep cannot be empty or null")
  private String cep;
  @NotBlank(message = "StudentEntity document cannot be empty or null")
  private String document;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime startDate;
  private List<Subjects> subjects;
}
