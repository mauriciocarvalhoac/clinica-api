package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumAbrangencia;
import com.mrc.clinic.clinic_api.entity.enums.EnumAcomodacao;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class PlanoDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Campo descrição não pode ser nulo")
    @Size(max = 50, message = "O máximo para o campo descrição é de 50 caracteres")
    private String descricao;

    @NotNull(message = "Campo Código ANS não pode ser nulo")
    @Size(max = 20, message = "O máximo para o campo descrição é de 20 caracteres")
    private String codigoAns;

    @NotNull(message = "Campo Acomodação não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private EnumAcomodacao acomodacao;

    @NotNull(message = "Campo Abrangência não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private EnumAbrangencia abrangencia;

    @NotNull(message = "Campo Situação não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private EnumSituacao situacao;

    private LocalDateTime dataCriacao;
}
