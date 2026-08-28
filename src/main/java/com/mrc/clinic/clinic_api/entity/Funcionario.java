package com.mrc.clinic.clinic_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_funcionario")
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String nome;
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;
    @Column(length = 15)
    private String rg;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 15, nullable = false)
    private String celular;
    @Column(length = 15)
    private String telefone;
    @Column(length = 1)
    private String genero;
    @Column(name = "pais_origem", length = 3)
    private String paisOrigem;

    @Enumerated(EnumType.STRING)
    @Column(name = "funcao", length = 3)
    private EnumCargo funcao;
    @Column(name = "departamento", length = 100)
    private String departamento;
    @Column(name = "matricula", length = 20)
    private String matricula;

    @Column(name = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco = new Endereco();

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private Usuario usuario;

}
