package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Especialidade;
import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.EspecialidadeRepository;
import com.mrc.clinic.clinic_api.service.EspecialidadeService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
        Optional<Especialidade> opt = repository.findByDescricao(dto.getDescricao());
        if (opt.isEmpty()) {
            Especialidade save = repository.save(to(dto));
            return to(save);
        }
        throw new ObjectNotFoundException("Essa especialidade já está cadastrada.");
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

    @Override
    public @Nullable EspecialidadeDTO findById(Long id) {
        return repository.findById(id).map(this::to).orElseThrow(() -> new ObjectNotFoundException("Especialidade não encontrado."));
    }

    @Override
    public @Nullable EspecialidadeDTO update(Long id, EspecialidadeDTO dto) {
        Optional<Especialidade> opt = repository.findById(id);
        if (opt.isPresent()) {
            Optional<Especialidade> descricao = repository.findByDescricao(dto.getDescricao());
            if (descricao.isPresent() && !id.equals(descricao.get().getId())) {
                throw new ObjectExistingException("Descrição já existe na base de dados.");
            }
            dto.setId(id);
            Especialidade save = repository.save(to(dto));
            return to(save);
        }
        throw new ObjectNotFoundException("Id " + id + " não existe.");
    }

    @Override
    public @Nullable List<EspecialidadeDTO> filterBy(String descricao) {
        Especialidade obj = new Especialidade();
        obj.setDescricao(descricao);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Especialidade> example = Example.of(obj, matcher);
        return repository.findAll(example).stream().map(this::to)
                .sorted(Comparator.comparing(EspecialidadeDTO::getDescricao)).collect(Collectors.toList());
    }

    private Especialidade to(EspecialidadeDTO dto) {
        Especialidade obj = new Especialidade();
        obj.setId(dto.getId());
        obj.setDescricao(dto.getDescricao());
        obj.setCbo(dto.getCbo());
        obj.setTiss(dto.getTiss());
        return obj;
    }

    private EspecialidadeDTO to(Especialidade obj) {
        EspecialidadeDTO dto = new EspecialidadeDTO();
        dto.setId(obj.getId());
        dto.setDescricao(obj.getDescricao());
        dto.setCbo(obj.getCbo());
        dto.setTiss(obj.getTiss());
        return dto;
    }

}
