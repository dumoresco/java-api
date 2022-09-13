package br.com.forttiori.mongodb.v1.model.Student;

import br.com.forttiori.mongodb.v1.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.v1.persistence.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = {"id","firstName", "lastName", "age", "email", "startDate", "subjects"})
public class StudentResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("document")
    private String document;

    @JsonProperty("address")
    private AddressEntity address;

    @JsonProperty("startDate")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startDate;

}
