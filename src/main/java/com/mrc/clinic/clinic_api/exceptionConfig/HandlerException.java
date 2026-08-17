package com.mrc.clinic.clinic_api.exceptionConfig;

import com.mrc.clinic.clinic_api.exceptionConfig.dto.ErrorField;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorField> handlerObjectNotFoundException(ObjectNotFoundException ex) {
        ErrorField erro = new ErrorField();
        erro.setMessage(ex.getMessage());
        erro.setTime(LocalDateTime.now());
        erro.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ObjectExistingException.class)
    public ResponseEntity<ErrorField> handlerObjectExistingException(ObjectExistingException ex) {
        ErrorField erro = new ErrorField();
        erro.setMessage(ex.getMessage());
        erro.setTime(LocalDateTime.now());
        erro.setStatus(HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorField> handlerDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorField erro = new ErrorField();
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : "Há um erro de integridade de dados.";
        if (message.contains("null") || message.contains("not-null")) {
            message = "Há campos não preenchidos ou preenchidos incorretamente.";
        }
        erro.setMessage(message);
        erro.setTime(LocalDateTime.now());
        erro.setStatus(HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

}
