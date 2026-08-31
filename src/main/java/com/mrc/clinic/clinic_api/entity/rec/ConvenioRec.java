package com.mrc.clinic.clinic_api.entity.rec;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;

public record ConvenioRec(
        Long id,
        String descricao,
        EnumSituacao situacao
) {
}
