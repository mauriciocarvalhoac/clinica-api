package com.mrc.clinic.clinic_api.entity.rec;

import com.mrc.clinic.clinic_api.entity.EnumCargo;

public record FuncionarioRec(
        Long id,
        String nome,
        String cpf,
        String email,
        EnumCargo funcao) {
}
