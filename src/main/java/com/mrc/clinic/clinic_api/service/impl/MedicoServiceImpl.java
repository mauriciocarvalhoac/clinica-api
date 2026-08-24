package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Especialidade;
import com.mrc.clinic.clinic_api.entity.Medico;
import com.mrc.clinic.clinic_api.entity.MedicoEspecialidade;
import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import com.mrc.clinic.clinic_api.entity.dto.MedicoDTO;
import com.mrc.clinic.clinic_api.entity.dto.MedicoEspecialidadeDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.MedicoRepository;
import com.mrc.clinic.clinic_api.service.MedicoService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    private MedicoRepository repository;

    @Override
    public MedicoDTO save(MedicoDTO dto) {
        Optional<Medico> ooptCpf = repository.findByCpf(dto.getCpf());
        if (ooptCpf.isPresent()) {
            throw new ObjectExistingException("Esse CPF já existe.");
        }
        Medico saved = repository.save(to(dto));
        return to(saved);
    }

    @Override
    public List<MedicoDTO> listAll() {
        return repository.listAll().stream()
                .map(this::to)
                .sorted(Comparator.comparing(MedicoDTO::getNome))
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDTO findById(Long id) {
        return repository.findMedicoById(id)
                .map(this::to)
                .orElseThrow(() -> new ObjectNotFoundException("Médico não encontrado."));
    }

    @Override
    public Long delete(Long id) {
        Optional<Medico> opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.deleteById(id);
            return id;
        }
        throw new ObjectNotFoundException("Id " + id + " não pode ser excluído.");
    }

    @Override
    @Transactional
    public @Nullable MedicoDTO update(Long id, MedicoDTO dto) {
        Medico medico = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Id " + id + " não encontrado."));

        Optional<Medico> optCpf = repository.findByCpf(dto.getCpf());
        if (optCpf.isPresent() && !id.equals(optCpf.get().getId())) {
            throw new ObjectExistingException("CPF já existe na base de dados.");
        }

        dto.setId(id);
        toMedico(dto, medico);
        atualizarEspecialidades(dto, medico);

        return to(medico);
    }

    private void atualizarEspecialidades(MedicoDTO dto, Medico medico) {
        Set<Long> idsNoDto = dto.getMedicoEspecialidades().stream()
                .map(MedicoEspecialidadeDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        medico.getMedicoEspecialidades().removeIf(medEsp -> !idsNoDto.contains(medEsp.getId()));

        for (MedicoEspecialidadeDTO medEspDTO : dto.getMedicoEspecialidades()) {
            if (medEspDTO.getId() != null) {
                medico.getMedicoEspecialidades().stream()
                        .filter(medEsp -> medEsp.getId().equals(medEspDTO.getId()))
                        .findFirst()
                        .ifPresent(medEsp -> {
                            medEsp.setPrincipal(medEspDTO.getPrincipal());
                            medEsp.setSituacao(medEspDTO.getSituacao());
                        });
            } else {
                MedicoEspecialidade medesp = new MedicoEspecialidade();
                medesp.setEspecialidade(new Especialidade());
                medesp.getEspecialidade().setId(medEspDTO.getEspecialidade().getId());
                medesp.setPrincipal(medEspDTO.getPrincipal());
                medesp.setSituacao(medEspDTO.getSituacao());
                medesp.setMedico(medico);
                medico.getMedicoEspecialidades().add(medesp);
            }
        }

    }

    private void toMedico(MedicoDTO dto, Medico obj) {
        obj.setId(dto.getId());
        obj.setNome(dto.getNome());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCelular(dto.getCelular());
        obj.setPaisOrigem(dto.getPaisOrigem());
        obj.setDataNascimento(dto.getDataNascimento());
        obj.setGenero(dto.getGenero());
        obj.setRg(dto.getRg());
        obj.getEndereco().setCep(dto.getEndereco().getCep());
        obj.getEndereco().setLogradouro(dto.getEndereco().getLogradouro());
        obj.getEndereco().setNumero(dto.getEndereco().getNumero());
        obj.getEndereco().setBairro(dto.getEndereco().getBairro());
        obj.getEndereco().setCidade(dto.getEndereco().getCidade());
        obj.getEndereco().setEstado(dto.getEndereco().getEstado());
    }

    @Override
    public List<MedicoDTO> filterBy(String nome, String cpf) {
        Medico obj = new Medico();
        obj.setNome(nome);
        obj.setCpf(cpf);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Medico> example = Example.of(obj, matcher);

        return repository.findAll(example).stream().map(this::to).collect(Collectors.toList());
    }

    public MedicoDTO to(Medico obj) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setCpf(obj.getCpf());
        dto.setEmail(obj.getEmail());
        dto.setTelefone(obj.getTelefone());
        dto.setCelular(obj.getCelular());
        dto.setDataNascimento(obj.getDataNascimento());
        dto.setPaisOrigem(obj.getPaisOrigem());
        dto.setGenero(obj.getGenero());
        dto.setRg(obj.getRg());
        dto.getEndereco().setCep(obj.getEndereco().getCep());
        dto.getEndereco().setLogradouro(obj.getEndereco().getLogradouro());
        dto.getEndereco().setNumero(obj.getEndereco().getNumero());
        dto.getEndereco().setBairro(obj.getEndereco().getBairro());
        dto.getEndereco().setCidade(obj.getEndereco().getCidade());
        dto.getEndereco().setEstado(obj.getEndereco().getEstado());

        for (MedicoEspecialidade medEsp : obj.getMedicoEspecialidades()) {
            MedicoEspecialidadeDTO medespDTO = new MedicoEspecialidadeDTO();
            medespDTO.setEspecialidade(new EspecialidadeDTO());
            medespDTO.getEspecialidade().setId(medEsp.getEspecialidade().getId());
            medespDTO.getEspecialidade().setDescricao(medEsp.getEspecialidade().getDescricao());
            medespDTO.getEspecialidade().setCbo(medEsp.getEspecialidade().getCbo());
            medespDTO.getEspecialidade().setTiss(medEsp.getEspecialidade().getTiss());
            medespDTO.setPrincipal(medEsp.getPrincipal());
            medespDTO.setSituacao(medEsp.getSituacao());
            MedicoDTO medicoDTO = new MedicoDTO();
            medespDTO.setId(medEsp.getId());
            medespDTO.setMedico(medicoDTO);
            dto.getMedicoEspecialidades().add(medespDTO);
        }

        return dto;
    }

    public Medico to(MedicoDTO dto) {
        Medico obj = new Medico();
        obj.setId(dto.getId());
        obj.setNome(dto.getNome());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCelular(dto.getCelular());
        obj.setPaisOrigem(dto.getPaisOrigem());
        obj.setDataNascimento(dto.getDataNascimento());
        obj.setGenero(dto.getGenero());
        obj.setRg(dto.getRg());
        obj.getEndereco().setCep(dto.getEndereco().getCep());
        obj.getEndereco().setLogradouro(dto.getEndereco().getLogradouro());
        obj.getEndereco().setNumero(dto.getEndereco().getNumero());
        obj.getEndereco().setBairro(dto.getEndereco().getBairro());
        obj.getEndereco().setCidade(dto.getEndereco().getCidade());
        obj.getEndereco().setEstado(dto.getEndereco().getEstado());

        for (MedicoEspecialidadeDTO medEspDTO : dto.getMedicoEspecialidades()) {
            MedicoEspecialidade medesp = new MedicoEspecialidade();
            medesp.setEspecialidade(new Especialidade());
            medesp.getEspecialidade().setId(medEspDTO.getEspecialidade().getId());
            medesp.getEspecialidade().setDescricao(medEspDTO.getEspecialidade().getDescricao());
            medesp.getEspecialidade().setCbo(medEspDTO.getEspecialidade().getCbo());
            medesp.getEspecialidade().setTiss(medEspDTO.getEspecialidade().getTiss());
            medesp.setPrincipal(medEspDTO.getPrincipal());
            medesp.setSituacao(medEspDTO.getSituacao());
            medesp.setMedico(obj);
            obj.getMedicoEspecialidades().add(medesp);
        }

        return obj;
    }
}
