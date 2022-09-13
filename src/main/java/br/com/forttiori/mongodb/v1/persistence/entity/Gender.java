package br.com.forttiori.mongodb.v1.persistence.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
 public enum Gender {
    @JsonProperty(value = "male")
    MALE("male"),
    @JsonProperty(value = "female")
    FEMALE("female"),
    @JsonProperty(value = "other")
     OTHER("other");

    private final String genero;


   @JsonCreator
   public static Gender findGender(String gender) {
      return EnumSet.allOf((Gender.class))
              .stream()
              .filter(t -> t.getGenero().equalsIgnoreCase(gender))
              .findFirst().orElse(null);
   }
}
