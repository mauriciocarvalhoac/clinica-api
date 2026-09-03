package com.mrc.clinic.clinic_api.entity;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_convenio")
public class Convenio extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "razao_social", length = 100, nullable = false)
    private String razaoSocial;
    @Column(name = "nome_fantasia", length = 100, nullable = false)
    private String nomeFantasia;
    @Column(name = "cnpj", length = 14, nullable = false, unique = true)
    private String cnpj;
    @Column(name = "registro_ans", length = 20, nullable = false)
    private String registroAns;
    @Column(name = "telefone", length = 11)
    private String telefone;
    @Column(name = "email", length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private EnumSituacao situacao;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_convenio")
    private List<Plano> planos;

    private LocalDateTime dataCriacao;
}
