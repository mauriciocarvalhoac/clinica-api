package com.mrc.clinic.clinic_api.entity.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String username;
    @Column(length = 20, nullable = false)
    private String password;
}
