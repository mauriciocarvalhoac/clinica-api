package com.mrc.clinic.clinic_api.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class AgendaDTO implements Serializable {
    private Long id;
    @NotNull(message = "O campo Descricao é obrigatório.")
    @Size(max = 150, message = "O valor maximo do campo agenda é 150 caracteres.")
    private String descricao;
    @NotNull(message = "O campo Duração é obrigatório.")
    private LocalTime duracao;
    private FuncionarioDTO funcionario;
    private EspecialidadeDTO especialidade;
    private String sala;

    private Boolean modalidadeParticular;
    private BigDecimal valorConsultaParticular;
    private Boolean modalidadeConvenio;
    private Set<ConvenioDTO> convenios;

    private List<AtendimentoDTO> atendimentos;

}
