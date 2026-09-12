package com.mrc.clinic.clinic_api.entity;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacaoFormacaoEducacional;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
    @Column(name = "crm", length = 11, nullable = false, unique = true)
    private String crm;
    @Column(name = "crm_estado", length = 2)
    private String crmEstado;
    @Column(name = "crm_situacao", length = 1)
    @Enumerated(EnumType.STRING)
    private EnumSituacao crmSituacao;

    private String instituicaoGraduacao;
    private String instituicaoPos;
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_pos", length = 1)
    private EnumSituacaoFormacaoEducacional situacaoPos;
    private String instituicaoMestrado;
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_mestrado", length = 1)
    private EnumSituacaoFormacaoEducacional situacaoMestrado;
    private String instituicaoDoutorado;
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_doutorado", length = 1)
    private EnumSituacaoFormacaoEducacional situacaoDoutorado;

    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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
