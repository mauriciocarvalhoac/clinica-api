package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacaoFormacaoEducacional;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MedicoDTO {
    private Long id;
    private String crm;
    private String crmEstado;

    private String instituicaoGraduacao;
    private String instituicaoPos;
    private String instituicaoMestrado;
    private String instituicaoDoutorado;

    @Enumerated(EnumType.STRING)
    private EnumSituacao crmSituacao;
    private EnumSituacaoFormacaoEducacional situacaoPos;
    private EnumSituacaoFormacaoEducacional situacaoMestrado;
    private EnumSituacaoFormacaoEducacional situacaoDoutorado;

    private List<MedicoEspecialidadeDTO> medicoEspecialidades = new ArrayList<>();
}
