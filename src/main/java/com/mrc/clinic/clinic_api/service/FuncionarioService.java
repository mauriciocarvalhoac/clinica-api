package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.FuncionarioDTO;

import java.util.List;

public interface FuncionarioService {
    FuncionarioDTO save(FuncionarioDTO dto);

    List<FuncionarioDTO> listAll();

    FuncionarioDTO findById(Long id);

    FuncionarioDTO update(Long id, FuncionarioDTO dto);

    Long delete(Long id);

    List<FuncionarioDTO> filterBy(String nome, String cpf);
}
