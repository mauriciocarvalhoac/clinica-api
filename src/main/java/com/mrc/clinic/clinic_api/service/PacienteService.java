package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.PacienteDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PacienteService {
    PacienteDTO save(PacienteDTO dto);

    List<PacienteDTO> listAll();

    PacienteDTO findById(Long id);

    @Nullable Long delete(Long id);

    @Nullable PacienteDTO update(Long id, PacienteDTO dto);
}
