package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.AgendaDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface AgendaService {
    AgendaDTO save(@Valid AgendaDTO dto);

    List<AgendaDTO> listAll();

    List<AgendaDTO> filterBy(String nome);

    AgendaDTO findAgenda(Long id);

    Long delete(Long id);

    AgendaDTO update(Long id, @Valid AgendaDTO dto);
}
