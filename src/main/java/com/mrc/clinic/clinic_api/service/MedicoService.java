package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.MedicoDTO;

import java.util.List;

public interface MedicoService {
    MedicoDTO save(MedicoDTO dto);

    List<MedicoDTO> listAll();

    MedicoDTO findById(Long id);

    Long delete(Long id);

    MedicoDTO update(Long id, MedicoDTO dto);
}
