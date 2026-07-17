package com.mrc.clinic.clinic_api.exceptionConfig;

import com.mrc.clinic.clinic_api.exceptionConfig.dto.ErrorField;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
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
}
