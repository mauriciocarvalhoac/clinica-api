package com.mrc.clinic.clinic_api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedicoEspecialidadeDTO implements Serializable {
    private Long id;

    private Boolean principal;
    private Boolean situacao;

    private MedicoDTO medico;
    private EspecialidadeDTO especialidade;


}
