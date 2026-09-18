package com.mrc.clinic.clinic_api.entity.enums;

import lombok.Getter;

@Getter
public enum EnumUserRoles {
    ADM("Administrador"),
    GES("Gestor"),
    MED("Médico"),
    MAU("Médico Auxiliar"),
    MAD("Médico + Administrador"),
    REC("Recepcionista"),
    ;
    private final String descricao;

    EnumUserRoles(String descricao) {
        this.descricao = descricao;
    }
}
