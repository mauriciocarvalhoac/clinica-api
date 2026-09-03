package com.mrc.clinic.clinic_api.entity.enums;

public enum EnumAbrangencia {
    E("Estadual"),
    M("Municipal"),
    N("Nacional"),
    R("Regional"),
    ;

    private final String descricao;

    EnumAbrangencia(String descricao) {
        this.descricao = descricao;
    }
}
