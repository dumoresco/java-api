package br.com.forttiori.mongodb.v1.model.Student;

import br.com.forttiori.mongodb.v1.persistence.entity.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {

  @Size(min = 3, message = "First Name must have at least {min} letters")
  @NotBlank(message = "First Name cannot be empty or null.")
  @ApiModelProperty(value = "Nome do estudante" , required = true, example = "Eduardo Moresco")
  private String firstName;
  @Size(min = 3, message = "Last name must have at least {min} letters")
  @NotBlank(message = "Last Name cannot be empty or null.")
  @ApiModelProperty(value = "Sobrenome do estudante", required = true, example = "Iost")
  private String lastName;
  @Positive(message = "Age must be greater than 0")
  @NotNull(message = "Age cannot be null")
  @ApiModelProperty(value = "Idade do estudante", required = true, example = "21")
  private Integer age;
  @Email(
      message = "StudentEntity email should be valid",
      regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
  @NotBlank(message = "StudentEntity e-mail cannot be empty or null.")
  @ApiModelProperty(value = "Email do estudante", required = true, example = "eduardoiost@outlook.com")
  private String email;
  @ApiModelProperty(value = "Gênero do estudante", required = true, example = "male")
  private Gender gender;
  @NotBlank(message = "StudentEntity cep cannot be empty or null")
  @ApiModelProperty(value = "Endereço do estudante", required = true, example = "91740840")
  private String cep;
  @NotBlank(message = "StudentEntity document cannot be empty or null")
  @ApiModelProperty(value = "Documento do estudante", required = true, example = "87744163087")
  private String document;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @ApiModelProperty(value = "Data de matrícula do estudante")
  private LocalDateTime startDate;

}
