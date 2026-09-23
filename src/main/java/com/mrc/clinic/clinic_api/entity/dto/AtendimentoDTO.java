package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumDia;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
public class AtendimentoDTO implements Serializable {
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnumDia dia;
    private LocalTime horaIni;
    private LocalTime intervaloIni;
    private LocalTime intervaloFim;
    private LocalTime horaFim;
    private AgendaDTO agenda;

}
