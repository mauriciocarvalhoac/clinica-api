package com.mrc.clinic.clinic_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_medico_especialidade",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_medico_especialidade",
                        columnNames = {"id_medico", "id_especialidade"}
                )
        })
public class MedicoEspecialidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "situacao", nullable = false)
    private Boolean situacao;
    @Column(name = "principal", nullable = false)
    private Boolean principal;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidade", nullable = false)
    private Especialidade especialidade;

}
