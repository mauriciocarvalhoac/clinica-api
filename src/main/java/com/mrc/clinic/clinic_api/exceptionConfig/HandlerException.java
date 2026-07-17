package com.mrc.clinic.clinic_api.exceptionConfig;

import com.mrc.clinic.clinic_api.exceptionConfig.dto.ErrorField;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ConflictException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorField> handlerObjectNotFoundException(ObjectNotFoundException e) {
        ErrorField erro = new ErrorField();
        erro.setMessage(e.getMessage());
        erro.setTime(LocalDateTime.now());
        erro.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorField> handlerConflictException(ConflictException e) {
        ErrorField erro = new ErrorField();
        erro.setMessage(e.getMessage());
        erro.setTime(LocalDateTime.now());
        erro.setStatus(HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
}
