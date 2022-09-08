package br.com.forttiori.mongodb.v1.persistence.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@Getter
@AllArgsConstructor
public enum Subjects {
    @JsonProperty(value = "matematica")
    MATEMATICA("matematica"),
    @JsonProperty(value = "portugues")
    PORTUGUES("portugues"),
    @JsonProperty(value = "ingles")
    INGLES("ingles"),
    @JsonProperty(value = "historia")
    HISTORIA("historia"),
    @JsonProperty(value = "geografia")
    GEOGRAFIA("geografia");


    private String subjects;


    @JsonCreator
    public static Subjects findGender(String subjects) {
        return EnumSet.allOf((Subjects.class))
                .stream()
                .filter(t -> t.setSubjects().equalsIgnoreCase(subjects))
                .findFirst().orElse(null);
    }

    private String setSubjects() {
        return subjects;
    }
}
