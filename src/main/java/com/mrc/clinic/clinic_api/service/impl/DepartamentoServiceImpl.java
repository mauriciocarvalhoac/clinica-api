package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Departamento;
import com.mrc.clinic.clinic_api.entity.dto.DepartamentoDTO;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.DepartamentoRepository;
import com.mrc.clinic.clinic_api.service.DepartamentoService;
import com.mrc.clinic.clinic_api.util.MsgUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl extends AbstractServiceImpl implements DepartamentoService {
    @Autowired
    private DepartamentoRepository repository;

    @Override
    public DepartamentoDTO save(@Valid @RequestBody DepartamentoDTO dto) {
        Optional<Departamento> opt = repository.findByDescricao(dto.getDescricao());
        if (opt.isPresent()) {
            throw new ObjectExistingException(MsgUtil.ITEM_EXISTENTE.replace("%s", dto.getDescricao()));
        }

        Departamento saved = repository.save(to(dto));
        return to(saved);
    }

    @Override
    public List<DepartamentoDTO> findAll() {
        return repository.findAll().stream().map(this::to).sorted(Comparator.comparing(DepartamentoDTO::getDescricao, String.CASE_INSENSITIVE_ORDER)).toList();
    }

    @Override
    public List<DepartamentoDTO> findAllAtivos() {
        return repository.findBySituacao(EnumSituacao.A).stream().map(this::to).toList();
    }

    @Override
    public DepartamentoDTO findById(Long id) {
        return repository.findById(id).map(this::to).orElseThrow(() -> new ObjectNotFoundException(MsgUtil.ITEM_NAO_ENCONTRADO));
    }

    @Override
    public List<DepartamentoDTO> filter(String descricao, String situacao) {
        Departamento departamento = new Departamento();
        departamento.setDescricao(descricao);
        departamento.setSituacao((situacao != null && !situacao.equals("null")) ? EnumSituacao.valueOf(situacao) : null);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Departamento> example = Example.of(departamento, matcher);
        return repository.findAll(example).stream().map(this::to).toList();
    }

    @Override
    public DepartamentoDTO update(Long id, DepartamentoDTO dto) {
        Optional<Departamento> opt = repository.findByDescricao(dto.getDescricao());
        if (opt.isPresent() && !id.equals(opt.get().getId())) {
            throw new ObjectExistingException(MsgUtil.ITEM_EXISTENTE.replace("%s", dto.getDescricao()));
        }
        Departamento departamento = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(MsgUtil.ITEM_NAO_ENCONTRADO));

        dto.setId(id);
        to(dto, departamento);
        Departamento saved = repository.save(departamento);
        return to(saved);
    }

    @Override
    public Long delete(Long id) {
        Optional<Departamento> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw new ObjectNotFoundException(MsgUtil.ITEM_NAO_ENCONTRADO);
        }
        repository.deleteById(id);
        return id;
    }

}
