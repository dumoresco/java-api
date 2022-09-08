package br.com.forttiori.mongodb.v1.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentQuery {

    @Builder.Default
    private String firstName = "";

    @Builder.Default
    private String lastName = "";

}
