package br.com.eduardo.api.resources.exceptions;

import br.com.eduardo.api.services.exceptions.DataIntegratyViolationException;
import br.com.eduardo.api.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResrouceExceptionsHendler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> ObjectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
        StandartError error = new StandartError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandartError> DataIntegratyViolationException(DataIntegratyViolationException ex, HttpServletRequest request) {
        StandartError error = new StandartError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}