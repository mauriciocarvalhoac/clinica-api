package com.mrc.clinic.clinic_api.exceptionConfig.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
