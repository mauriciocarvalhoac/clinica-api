package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Convenio;
import com.mrc.clinic.clinic_api.entity.dto.ConvenioDTO;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import com.mrc.clinic.clinic_api.entity.rec.ConvenioRec;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.ConveinoRepository;
import com.mrc.clinic.clinic_api.service.ConvenioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvenioServiceImpl implements ConvenioService {

    @Autowired
    private ConveinoRepository repository;

    @Override
    public ConvenioDTO save(ConvenioDTO dto) {
        Convenio response = repository.save(to(dto));
        return to(response);
    }

    @Override
    public List<ConvenioDTO> listAll() {
        return repository.listAll().stream().map(this::to).toList();
    }

    @Override
    public Long delete(Long id) {
        Optional<Convenio> opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.deleteById(id);
            return id;
        }

        throw new ObjectExistingException("Item não encontrado.");
    }

    @Override
    public ConvenioDTO findById(Long id) {
        return repository.findById(id).map(this::to).orElseThrow(() -> new ObjectNotFoundException("O convênio não foi encontrado."));
    }

    @Override
    public ConvenioDTO update(Long id, ConvenioDTO dto) {
        repository.findById(id).ifPresent(c -> {
            to(dto, c);
            repository.save(c);
        });
        return findById(id);
    }

    @Override
    public List<ConvenioDTO> filterBy(String descricao, String situacao) {
        Convenio convenio = new Convenio();
        convenio.setDescricao(descricao);
        convenio.setSituacao((situacao == null || "null".equals(situacao)) ? null : EnumSituacao.valueOf(situacao));

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Convenio> example = Example.of(convenio, matcher);
        return repository.findAll(example).stream().map(this::to).toList();
    }

    private void to(ConvenioDTO dto, Convenio c) {
        BeanUtils.copyProperties(dto, c);
    }

    private ConvenioDTO to(Convenio response) {
        ConvenioDTO dto = new ConvenioDTO();
        BeanUtils.copyProperties(response, dto);
        return dto;
    }

    private ConvenioDTO to(ConvenioRec rec) {
        ConvenioDTO dto = new ConvenioDTO();
        BeanUtils.copyProperties(rec, dto);
        return dto;
    }

    private Convenio to(ConvenioDTO dto) {
        Convenio obj = new Convenio();
        BeanUtils.copyProperties(dto, obj);
        return obj;
    }
}
