package com.mrc.clinic.clinic_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_agenda")
public class Agenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150, nullable = false)
    private String descricao;
    private LocalTime duracao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;
    private String sala;
    private Boolean modalidadeParticular;
    private BigDecimal valorConsultaParticular;
    private Boolean modalidadeConvenio;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_agenda_convenio",
            joinColumns = @JoinColumn(name = "agenda_id"),
            inverseJoinColumns = @JoinColumn(name = "convenio_id"))
    private Set<Convenio> convenios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda", orphanRemoval = true)
    private List<Atendimento> atendimentos;
}
