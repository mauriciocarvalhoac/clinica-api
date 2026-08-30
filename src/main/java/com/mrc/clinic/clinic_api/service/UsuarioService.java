package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.UsuarioDTO;
import com.mrc.clinic.clinic_api.entity.rec.UsuarioRec;

import java.util.List;

public interface UsuarioService {
    UsuarioRec save(UsuarioDTO dto);

    UsuarioRec update(Long id, UsuarioDTO dto);

    List<UsuarioDTO> listAll();

    List<UsuarioDTO> filter(String username);
}
