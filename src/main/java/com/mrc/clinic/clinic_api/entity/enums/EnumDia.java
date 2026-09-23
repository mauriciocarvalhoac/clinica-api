package com.mrc.clinic.clinic_api.entity.enums;

import lombok.Getter;

@Getter
public enum EnumDia {
    DOM(0, "Domingo"),
    SEG(1, "Segunda"),
    TER(2, "Terça"),
    QUA(3, "Quarta"),
    QUI(4, "Quinta"),
    SEX(5, "Sexta"),
    SAB(6, "Sábado");

    private final int dia;
    private final String descricao;

    EnumDia(int dia, String descricao) {
        this.dia = dia;
        this.descricao = descricao;
    }
}
