package br.com.forttiori.mongodb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class StudentExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(value = NOT_FOUND)
  public StandardError entityNotFound(
      EntityNotFoundException e, HttpServletRequest request) {

    StandardError error = new StandardError();

    return (StandardError.builder().timestamp(Instant.now()).status(NOT_FOUND.value()).error("StudentEntity not found").message(NOT_FOUND.name()).path(request.getRequestURI())).build();

//    error.setTimestamp(Instant.now());
//    error.setStatus(NOT_FOUND.value());
//    error.setError("StudentEntity not found");
//    error.setMessage(NOT_FOUND.name());
//    error.setPath(request.getRequestURI());
//    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationExceptionDetails> notValid(MethodArgumentNotValidException e) {

    List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();

    String fields =
        fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(", "));
    String fieldsMessage =
        fieldErrorList.stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));

    return new ResponseEntity<>(ValidationExceptionDetails.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .fields(fields)
            .error("Invalid args")
            .path(e.getBindingResult().getNestedPath())
            .message(e.getMessage())
            .fieldsMessage(fieldsMessage)
            .build(), HttpStatus.BAD_REQUEST);
  }
}
