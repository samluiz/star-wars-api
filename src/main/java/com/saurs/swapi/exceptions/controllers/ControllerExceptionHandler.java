package com.saurs.swapi.exceptions.controllers;

import com.saurs.swapi.exceptions.services.CannotBeNullException;
import com.saurs.swapi.exceptions.services.InvalidUUIDException;
import com.saurs.swapi.exceptions.services.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        String error = "Object not found";

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidUUIDException.class)
    public ResponseEntity<StandardError> invalidUUID(InvalidUUIDException e, HttpServletRequest request) {

        String error = "Invalid UUID";

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(CannotBeNullException.class)
    public ResponseEntity<StandardError> cannotBeNull(CannotBeNullException e, HttpServletRequest request) {

        String error = "Field can't be null";

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
