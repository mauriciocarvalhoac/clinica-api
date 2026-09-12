package com.mrc.clinic.clinic_api.entity;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_especialidade")
public class Especialidade extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, nullable = false)
    private String descricao;
    @Column(name = "rqe", nullable = false)
    private String rqe;
    @Column(length = 20)
    private String cbo;
    @Column(length = 20)
    private String tiss;
    @Column(length = 1)
    @Enumerated(value = EnumType.STRING)
    private EnumSituacao situacao;
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "especialidade")
    private List<MedicoEspecialidade> medicoEspecialidades = new ArrayList<>();
}
