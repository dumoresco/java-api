package br.com.forttiori.mongodb.v1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
public class StandardError implements Serializable {

    private Integer status;
    private String message;

}
