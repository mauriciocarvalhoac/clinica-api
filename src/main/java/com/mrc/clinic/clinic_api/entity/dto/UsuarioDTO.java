package com.mrc.clinic.clinic_api.entity.dto;

import com.mrc.clinic.clinic_api.entity.enums.EnumUserRoles;
import com.mrc.clinic.clinic_api.entity.enums.EnumUserSituacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    @NotNull(message = "O campo Username é Obrigatório.")
    @Size(max = 100, message = "O valor maximo do campo Username é 50 caracteres.")
    private String username;

    @NotNull(message = "O campo Username é Obrigatório.")
    @Size(max = 250, message = "O valor maximo do campo Password é 16 caracteres.")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo Role é Obrigatório.")
    private EnumUserRoles role;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo Situação é Obrigatório.")
    private EnumUserSituacao situacao;

}
