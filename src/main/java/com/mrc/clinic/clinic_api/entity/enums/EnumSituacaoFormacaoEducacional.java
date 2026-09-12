package com.mrc.clinic.clinic_api.entity.enums;

import lombok.Getter;

@Getter
public enum EnumSituacaoFormacaoEducacional {
    N("Não Fiz"),
    E("Em Andamento"),
    C("Concluído");

    private final String descricao;

    EnumSituacaoFormacaoEducacional(String descricao) {
        this.descricao = descricao;
    }
}
