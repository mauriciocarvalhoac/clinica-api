package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDTO {
    private Long id;

    @NotNull(message = "O campo Descrição é obrigatório.")
    @Size(max = 100, message = "O valor maximo do campo Descrição é 100 caracteres.")
    private String descricao;

    @NotNull(message = "O campo Situação é obrigatório.")
    @Enumerated(value = EnumType.STRING)
    private EnumSituacao situacao;
}
