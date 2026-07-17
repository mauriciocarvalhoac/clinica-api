package com.mrc.clinic.clinic_api.exceptionConfig.exceptions;

public class ObjectExistingException extends RuntimeException {

    public ObjectExistingException(String message) {
        super(message);
    }
}
