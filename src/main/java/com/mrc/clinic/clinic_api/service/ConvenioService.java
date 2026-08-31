package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.ConvenioDTO;

import java.util.List;

public interface ConvenioService {

    ConvenioDTO save(ConvenioDTO dto);

    List<ConvenioDTO> listAll();

    Long delete(Long id);

    ConvenioDTO findById(Long id);

    ConvenioDTO update(Long id, ConvenioDTO dto);

    List<ConvenioDTO> filterBy(String descricao, String situacao);
}
