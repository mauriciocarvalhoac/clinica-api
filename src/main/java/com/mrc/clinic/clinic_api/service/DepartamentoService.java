package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.DepartamentoDTO;

import java.util.List;

public interface DepartamentoService {
    DepartamentoDTO save(DepartamentoDTO dto);

    List<DepartamentoDTO> findAll();

    List<DepartamentoDTO> findAllAtivos();

    DepartamentoDTO findById(Long id);

    List<DepartamentoDTO> filter(String descricao, String situacao);

    DepartamentoDTO update(Long id, DepartamentoDTO dto);

    Long delete(Long id);
}
