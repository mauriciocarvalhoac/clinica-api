package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Especialidade;
import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import com.mrc.clinic.clinic_api.repository.EspecialidadeRepository;
import com.mrc.clinic.clinic_api.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {

    @Autowired
    private EspecialidadeRepository repository;


    @Override
    public List<EspecialidadeDTO> listAll() {
        return repository.findAll().stream().map(this::to).toList();
    }

    private EspecialidadeDTO to(Especialidade obj) {
        EspecialidadeDTO dto = new EspecialidadeDTO();
        dto.setId(obj.getId());
        dto.setDescricao(obj.getDescricao());
        return dto;
    }

}
