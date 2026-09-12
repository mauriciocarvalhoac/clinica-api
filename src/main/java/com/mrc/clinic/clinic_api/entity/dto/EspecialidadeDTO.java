package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EspecialidadeDTO implements Serializable {
    private Long id;
    @NotNull(message = "O campo Descrição é obrigatório.")
    @Size(max = 200, message = "O valor maximo do campo Descrição é 200 caracteres.")
    private String descricao;
    @Size(max = 10, message = "O valor maximo do campo RQE é 10 caracteres.")
    private String rqe;
    @Size(max = 20, message = "O valor maximo do campo CBO é 20 caracteres.")
    private String cbo;
    @Size(max = 20, message = "O valor maximo do campo TUSS é 20 caracteres.")
    private String tiss;
    @Enumerated(value = EnumType.STRING)
    private EnumSituacao situacao;

    private List<MedicoEspecialidadeDTO> medicoEspecialidades = new ArrayList<>();

}
