package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.UsuarioDTO;
import com.mrc.clinic.clinic_api.entity.rec.UsuarioRec;

public interface UsuarioService {
    UsuarioRec save(UsuarioDTO dto);
}
