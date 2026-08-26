package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface EspecialidadeService {

    List<EspecialidadeDTO> listAll();

    @Nullable EspecialidadeDTO save(EspecialidadeDTO dto);

    @Nullable Long delete(Long id);

    @Nullable EspecialidadeDTO findById(Long id);

    @Nullable EspecialidadeDTO update(Long id, EspecialidadeDTO dto);

    List<EspecialidadeDTO> filterBy(String descricao, String situacao);
}
