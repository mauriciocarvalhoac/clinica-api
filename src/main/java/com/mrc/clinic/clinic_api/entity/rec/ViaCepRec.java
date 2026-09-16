package com.mrc.clinic.clinic_api.entity.rec;

public record ViaCepRec(
        String cep,
        String estado,
        String uf,
        String localidade,
        String bairro,
        String logradouro,
        String complemento
) {
}
