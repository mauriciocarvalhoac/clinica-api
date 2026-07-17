package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.PacienteDTO;

import java.util.List;

public interface PacienteService {
    PacienteDTO save(PacienteDTO dto);

    List<PacienteDTO> listAll();

    PacienteDTO findById(Long id);

}
