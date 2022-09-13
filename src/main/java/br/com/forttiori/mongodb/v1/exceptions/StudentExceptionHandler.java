package br.com.forttiori.mongodb.v1.exceptions;

import org.jetbrains.annotations.NotNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class StudentExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(value = NOT_FOUND)
  public StandardError entityNotFound(EntityNotFoundException e, HttpServletRequest request) {

    StandardError error = new StandardError();

    return (StandardError.builder().status(NOT_FOUND.value()).message(NOT_FOUND.name())).build();
  }

  @ResponseStatus(value = BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public StandardError notValid(@NotNull MethodArgumentNotValidException e) {

    List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();

    String fields =
        fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(", "));
    var as =
        fieldErrorList.stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));

    return new StandardError(
        StandardError.builder().status(BAD_REQUEST.value()).message(e.getMessage()).build());
  }
}
