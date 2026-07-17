package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Medico;
import com.mrc.clinic.clinic_api.entity.dto.MedicoDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.MedicoRepository;
import com.mrc.clinic.clinic_api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    private MedicoRepository repository;

    @Override
    public MedicoDTO save(MedicoDTO dto) {
        Optional<Medico> sad = repository.findByCpf(dto.getCpf());
        if (sad.isEmpty()) {
            Medico saved = repository.save(to(dto));
            return to(saved);
        }
        throw new ObjectExistingException("Esse CPF já existe.");
    }

    @Override
    public List<MedicoDTO> listAll() {
        return repository.findAll().stream().map(this::to).collect(Collectors.toList());
    }

    @Override
    public MedicoDTO findById(Long id) {
        return repository.findById(id)
                .map(this::to)
                .orElseThrow(() -> new ObjectNotFoundException("Médico não encontrado."));
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
