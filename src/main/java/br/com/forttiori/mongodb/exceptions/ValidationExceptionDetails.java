package br.com.forttiori.mongodb.exceptions;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Data
public class ValidationExceptionDetails extends StandardError{
    public String fields;
    public String fieldsMessage;

}
