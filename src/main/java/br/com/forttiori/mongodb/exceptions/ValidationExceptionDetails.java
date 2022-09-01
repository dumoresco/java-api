package br.com.forttiori.mongodb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ValidationExceptionDetails extends StandardError{


    public ValidationExceptionDetails(ValidationExceptionDetails invalid_args) {
    }
}
