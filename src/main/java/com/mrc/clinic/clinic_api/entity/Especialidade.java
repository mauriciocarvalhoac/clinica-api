package com.mrc.clinic.clinic_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
    @Column(length = 20)
    private String cbo;
    @Column(length = 20)
    private String tiss;
}
