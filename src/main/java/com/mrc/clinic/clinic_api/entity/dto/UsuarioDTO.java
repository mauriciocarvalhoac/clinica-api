package com.mrc.clinic.clinic_api.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
}
