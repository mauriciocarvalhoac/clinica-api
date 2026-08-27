package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Funcionario;
import com.mrc.clinic.clinic_api.entity.dto.FuncionarioDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.FuncionarioRepository;
import com.mrc.clinic.clinic_api.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl extends AbstractServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Override
    @Transactional
    public FuncionarioDTO save(FuncionarioDTO dto) {
        Optional<Funcionario> opt = repository.findByCpf(dto.getCpf());
        if (opt.isPresent()) {
            throw new ObjectExistingException("Esse CPF já existe.");
        }
        Funcionario saved = repository.save(to(dto));
        return to(saved);
    }

    @Override
    public List<FuncionarioDTO> listAll() {
        return repository.list().stream()
                .map(this::to)
                .sorted(Comparator.comparing(FuncionarioDTO::getNome))
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioDTO findById(Long id) {
        return repository.findById(id)
                .map(this::to)
                .orElseThrow(() -> new ObjectNotFoundException("Médico não encontrado."));
    }

    @Override
    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        Funcionario obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Id " + id + " não encontrado."));

        Optional<Funcionario> optCpf = repository.findByCpf(dto.getCpf());
        if (optCpf.isPresent() && !id.equals(optCpf.get().getId())) {
            throw new ObjectExistingException("CPF já existe na base de dados.");
        }

        dto.setId(id);
        to(dto, obj);
//        atualizarEspecialidades(dto, Funcionario);

        return to(obj);
    }


    @Override
    public Long delete(Long id) {
        Optional<Funcionario> opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.deleteById(id);
            return id;
        }
        throw new ObjectNotFoundException("Id " + id + " não pode ser excluído.");
    }

    @Override
    public List<FuncionarioDTO> filterBy(String nome, String cpf) {
        Funcionario obj = new Funcionario();
        obj.setNome(nome);
        obj.setCpf(cpf);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Funcionario> example = Example.of(obj, matcher);

        return repository.findAll(example).stream().map(this::to).collect(Collectors.toList());
    }

    @Override
    public FuncionarioDTO to(Funcionario obj) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setCpf(obj.getCpf());
        dto.setEmail(obj.getEmail());
        dto.setFuncao(obj.getFuncao());
        return dto;
    }
//
//    private void atualizarEspecialidades(FuncionarioDTO dto, Funcionario Funcionario) {
//        Set<Long> idsNoDto = dto.getFuncionarioEspecialidades().stream()
//                .map(FuncionarioEspecialidadeDTO::getId)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toSet());
//
//        Funcionario.getFuncionarioEspecialidades().removeIf(medEsp -> !idsNoDto.contains(medEsp.getId()));
//
//        for (FuncionarioEspecialidadeDTO medEspDTO : dto.getFuncionarioEspecialidades()) {
//            if (medEspDTO.getId() != null) {
//                Funcionario.getFuncionarioEspecialidades().stream()
//                        .filter(medEsp -> medEsp.getId().equals(medEspDTO.getId()))
//                        .findFirst()
//                        .ifPresent(medEsp -> {
//                            medEsp.setPrincipal(medEspDTO.getPrincipal());
//                            medEsp.setSituacao(medEspDTO.getSituacao());
//                        });
//            } else {
//                FuncionarioEspecialidade medesp = new FuncionarioEspecialidade();
//                medesp.setEspecialidade(new Especialidade());
//                medesp.getEspecialidade().setId(medEspDTO.getEspecialidade().getId());
//                medesp.setPrincipal(medEspDTO.getPrincipal());
//                medesp.setSituacao(medEspDTO.getSituacao());
//                medesp.setFuncionario(Funcionario);
//                Funcionario.getFuncionarioEspecialidades().add(medesp);
//            }
//        }
//
//    }
//
//


}
