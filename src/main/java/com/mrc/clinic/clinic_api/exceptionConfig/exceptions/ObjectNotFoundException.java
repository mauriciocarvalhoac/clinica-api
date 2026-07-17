package com.mrc.clinic.clinic_api.exceptionConfig.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
