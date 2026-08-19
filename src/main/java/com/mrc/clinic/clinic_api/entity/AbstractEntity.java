package com.mrc.clinic.clinic_api.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractEntity {
    private LocalDateTime dataCriacao;

}
