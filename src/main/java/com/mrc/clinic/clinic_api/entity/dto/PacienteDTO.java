package com.mrc.clinic.clinic_api.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {
    private Long id;
    private String nome;
    private String cpf;

    private String email;
    private String telefone;
    private String celular;

}
