package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Especialidade;
import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.EspecialidadeRepository;
import com.mrc.clinic.clinic_api.service.EspecialidadeService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {

    @Autowired
    private EspecialidadeRepository repository;


    @Override
    public List<EspecialidadeDTO> listAll() {
        return repository.findAll().stream().map(this::to)
                .sorted(Comparator.comparing(EspecialidadeDTO::getDescricao, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable EspecialidadeDTO save(EspecialidadeDTO dto) {
        Especialidade save = repository.save(to(dto));
        return to(save);
    }

    @Override
    public @Nullable Long delete(Long id) {
        Optional<Especialidade> opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.deleteById(id);
            return id;
        }
        throw new ObjectNotFoundException("Id " + id + " não pode ser excluído.");
    }

    private Especialidade to(EspecialidadeDTO dto) {
        Especialidade obj = new Especialidade();
        obj.setId(dto.getId());
        obj.setDescricao(dto.getDescricao());
        obj.setCbo(dto.getCbo());
        obj.setTuss(dto.getTuss());
        return obj;
    }

    private EspecialidadeDTO to(Especialidade obj) {
        EspecialidadeDTO dto = new EspecialidadeDTO();
        dto.setId(obj.getId());
        dto.setDescricao(obj.getDescricao());
        dto.setCbo(obj.getCbo());
        dto.setTuss(obj.getTuss());
        return dto;
    }

}
