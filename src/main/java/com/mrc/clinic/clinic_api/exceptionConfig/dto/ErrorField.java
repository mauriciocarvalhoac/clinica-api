package com.mrc.clinic.clinic_api.exceptionConfig.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorField {
    private String message;
    private HttpStatus status;
    private LocalDateTime time;
}
