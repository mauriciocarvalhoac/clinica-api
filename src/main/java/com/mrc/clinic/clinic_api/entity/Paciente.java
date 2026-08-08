package com.mrc.clinic.clinic_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_paciente")
public class Paciente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 252, nullable = false)
    private String nome;
    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 100)
    private String email;
    @Column(length = 11, nullable = false)
    private String telefone;
    @Column(length = 11)
    private String celular;

}
