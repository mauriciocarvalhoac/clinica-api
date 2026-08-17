package com.mrc.clinic.clinic_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {
    @Column(length = 8)
    private String cep;
    @Column(length = 100)
    private String logradouro;
    @Column(length = 10)
    private String numero;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String cidade;
    @Column(length = 2)
    private String estado;

}
