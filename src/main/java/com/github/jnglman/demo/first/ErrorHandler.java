package com.github.jnglman.demo.first;

import com.github.jnglman.demo.first.model.Errors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class ErrorHandler {

    private final Function<FieldError, String> fieldErrorToString =
            fe -> String.format("%s.%s: %s", fe.getObjectName(), fe.getField(), fe.getDefaultMessage());
    private final Function<ObjectError, String> objectErrorToString =
            ge -> String.format("%s: %s", ge.getObjectName(), ge.getDefaultMessage());

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Errors> handleNotValidBody(BindException e) {
        return ResponseEntity.badRequest().body(
                new Errors(getErrorMessages(e))
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errors> handleNotValidArgument(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                new Errors(getErrorMessages(e.getBindingResult()))
        );
    }

    private List<String> getErrorMessages(BindingResult bindingResult) {
        return Stream.concat(
                bindingResult.getFieldErrors().stream().map(fieldErrorToString),
                bindingResult.getGlobalErrors().stream().map(objectErrorToString))
                .collect(Collectors.toList());
    }

}
