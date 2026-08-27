package com.mrc.clinic.clinic_api.entity;

import lombok.Getter;

@Getter
public enum EnumCargo {
    MED("Medico"),
    REC("Recepcionista"),
    SU1("Sipervisor 1"),
    SU2("Sipervisor 2"),
    GER("Gerente"),
    ADM("Administrador Geral");

    private final String cargo;

    EnumCargo(String cargo) {
        this.cargo = cargo;
    }
}
