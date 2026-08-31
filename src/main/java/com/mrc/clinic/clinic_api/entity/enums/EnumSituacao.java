package com.mrc.clinic.clinic_api.entity.enums;

public enum EnumSituacao {
    I("Inativo"),
    A("Ativo"),
    ;

    private final String descricao;

    EnumSituacao(String descricao) {
        this.descricao = descricao;
    }
}
