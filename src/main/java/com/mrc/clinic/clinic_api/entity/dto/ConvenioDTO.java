package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ConvenioDTO implements Serializable {
    private Long id;
    @Size(max = 100, message = "O valor maximo do campo Razao Social é 100 caracteres.")
    @NotNull(message = "O campo Razao Social é obrigatório.")
    private String razaoSocial;
    @NotNull(message = "O campo Nome Fantasia é obrigatório.")
    @Size(max = 100, message = "O valor maximo do campo Nome Fantasia é 100 caracteres.")
    private String nomeFantasia;
    @NotNull(message = "O campo CNPJ é obrigatório.")
    private String cnpj;
    @NotNull(message = "O campo Registro ANS é obrigatório.")
    private String registroAns;
    private String telefone;
    private String email;

    @NotNull(message = "O campo situacao é obrigatório.")
    @Enumerated(EnumType.STRING)
    private EnumSituacao situacao;

    private List<PlanoDTO> planos;
}
