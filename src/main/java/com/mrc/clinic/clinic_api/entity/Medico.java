package com.mrc.clinic.clinic_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Temporal;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_medico")
public class Medico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String nome;
    @Column(length = 11, nullable = false)
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

    @Temporal
    @Column(name = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Embedded
    private Endereco endereco;

    public Endereco getEndereco() {
        if (endereco == null)
            endereco = new Endereco();
        return endereco;
    }
}
