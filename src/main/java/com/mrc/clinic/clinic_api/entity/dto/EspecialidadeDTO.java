package com.mrc.clinic.clinic_api.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EspecialidadeDTO implements Serializable {
    private Long id;
    @NotNull(message = "O campo Descrição é obrigatório.")
    @Size(max = 200, message = "O valor maximo do campo Descrição é 200 caracteres.")
    private String descricao;


}
