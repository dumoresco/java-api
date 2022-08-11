package br.com.forttiori.mongodb.model.Student;

import br.com.forttiori.mongodb.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.persistence.entity.Gender;
import br.com.forttiori.mongodb.persistence.entity.Subjects;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = {"id","firstName", "lastName", "age", "email", "gender", "startDate", "subjects"})
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

    @JsonProperty("subjects")
    private List<Subjects> subjects;
}
