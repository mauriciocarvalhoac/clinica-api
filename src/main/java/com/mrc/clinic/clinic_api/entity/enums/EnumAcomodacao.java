package com.mrc.clinic.clinic_api.entity.enums;

public enum EnumAcomodacao {
    Q("Quarto"),
    A("Apartamento"),
    ;

    private final String descricao;

    EnumAcomodacao(String descricao) {
        this.descricao = descricao;
    }
}
