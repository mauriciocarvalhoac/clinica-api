package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.MedicoDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface MedicoService {
    MedicoDTO save(MedicoDTO dto);

    List<MedicoDTO> listAll();

    MedicoDTO findById(Long id);

    Long delete(Long id);

    MedicoDTO update(Long id, MedicoDTO dto);

    @Nullable List<MedicoDTO> filterBy(String nome, String cpf);
}
