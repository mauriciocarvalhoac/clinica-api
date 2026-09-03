package com.mrc.clinic.clinic_api.entity;

import com.mrc.clinic.clinic_api.entity.enums.EnumAbrangencia;
import com.mrc.clinic.clinic_api.entity.enums.EnumAcomodacao;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_plano")
public class Plano extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;

    @Column(name = "codigo_ans", length = 20, nullable = false)
    private String codigoAns;

    @Column(name = "acomodacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumAcomodacao acomodacao;

    @Column(name = "abrangencia", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumAbrangencia abrangencia;

    @Column(name = "situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumSituacao situacao;

    private LocalDateTime dataCriacao;
}
