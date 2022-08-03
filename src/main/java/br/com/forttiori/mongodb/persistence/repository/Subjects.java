package br.com.forttiori.mongodb.persistence.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Subjects {
    @JsonProperty("matematica")
    MATEMATICA,
    @JsonProperty("portugues")
    PORTUGUES,
    @JsonProperty("ingles")
    INGLES,
    @JsonProperty("historia")
    HISTORIA,
    @JsonProperty("geografia")
    GEOGRAFIA
}
