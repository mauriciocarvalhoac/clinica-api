package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacaoFormacaoEducacional;
import jakarta.persistence.Embedded;
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
    @Enumerated(EnumType.STRING)
    private EnumSituacao crmSituacao;

    private String instituicaoGraduacao;
    private String instituicaoPos;
    private EnumSituacaoFormacaoEducacional situacaoPos;
    private String instituicaoMestrado;
    private EnumSituacaoFormacaoEducacional situacaoMestrado;
    private String instituicaoDoutorado;
    private EnumSituacaoFormacaoEducacional situacaoDoutorado;

    @Embedded
    private EnderecoDTO endereco = new EnderecoDTO();

    private List<MedicoEspecialidadeDTO> medicoEspecialidades = new ArrayList<>();


}
