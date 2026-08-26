package com.mrc.clinic.clinic_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String crm;
    private String crmEstado;
    private String instituicaoGraduacao;
    private String statusPos;
    private String instituicaoPos;
    private String statusMestrado;
    private String instituicaoMestrado;
    private String statusDoutorado;
    private String instituicaoDoutorado;

    @Embedded
    private Endereco endereco = new Endereco();
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicoEspecialidade> medicoEspecialidades = new ArrayList<>();

    public void adicionarEspecialidade(MedicoEspecialidade medicoEspecialidade) {
        medicoEspecialidade.setMedico(this);
        medicoEspecialidades.add(medicoEspecialidade);
    }

    public void removerEspecialidade(MedicoEspecialidade medicoEspecialidade) {
        medicoEspecialidades.remove(medicoEspecialidade);
        medicoEspecialidade.setMedico(null);
    }

    @PrePersist
    public void prePersist() {
        if (medicoEspecialidades != null) {
            medicoEspecialidades.forEach(medEspecialidade -> {
                medEspecialidade.setMedico(this);
            });
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (medicoEspecialidades != null) {
            medicoEspecialidades.forEach(medEspecialidade -> {
                medEspecialidade.setMedico(this);
            });
        }
    }
}
