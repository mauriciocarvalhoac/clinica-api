package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Medico;
import com.mrc.clinic.clinic_api.entity.dto.MedicoDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.MedicoRepository;
import com.mrc.clinic.clinic_api.service.MedicoService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    private MedicoRepository repository;

    @Override
    public MedicoDTO save(MedicoDTO dto) {
        Optional<Medico> opt = repository.findByCpf(dto.getCpf());
        if (opt.isEmpty()) {
            Medico saved = repository.save(to(dto));
            return to(saved);
        }
        throw new ObjectExistingException("Esse CPF já existe.");
    }

    @Override
    public List<MedicoDTO> listAll() {
        return repository.findAll().stream()
                .map(this::to)
                .sorted(Comparator.comparing(MedicoDTO::getNome))
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDTO findById(Long id) {
        return repository.findById(id)
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
    public @Nullable MedicoDTO update(Long id, MedicoDTO dto) {
        Optional<Medico> optMedico = repository.findById(id);
        if (optMedico.isPresent()) {
            dto.setId(id);
            Optional<Medico> optCpf = repository.findByCpf(dto.getCpf());
            if (optCpf.isPresent() && !id.equals(optCpf.get().getId())) {
                throw new ObjectExistingException("CPF já existe na base de dados.");
            }
            Medico medicoSaved = repository.save(to(dto));
            return to(medicoSaved);
        }
        throw new ObjectNotFoundException("Id " + id + " não encontrado.");
    }

    public MedicoDTO to(Medico obj) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setCpf(obj.getCpf());
        dto.setEmail(obj.getEmail());
        dto.setTelefone(obj.getTelefone());
        dto.setCelular(obj.getCelular());
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
        return obj;
    }
}
