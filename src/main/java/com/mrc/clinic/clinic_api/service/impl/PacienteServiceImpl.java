package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Paciente;
import com.mrc.clinic.clinic_api.entity.dto.PacienteDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.PacienteRepository;
import com.mrc.clinic.clinic_api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository repository;

    @Override
    public PacienteDTO save(PacienteDTO dto) {
        Optional<Paciente> sad = repository.findByCpf(dto.getCpf());
        if (sad.isEmpty()) {
            Paciente saved = repository.save(to(dto));
            return to(saved);
        }
        throw new ObjectExistingException("Esse CPF já existe.");


    }

    @Override
    public PacienteDTO findById(Long id) {
        return repository.findById(id)
                .map(this::to)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente Não encontrado"));
    }

    public Paciente to(PacienteDTO dto) {
        Paciente obj = new Paciente();
        obj.setId(dto.getId());
        obj.setNome(dto.getNome());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCelular(dto.getCelular());
        return obj;
    }

    public PacienteDTO to(Paciente obj) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setCpf(obj.getCpf());
        dto.setEmail(obj.getEmail());
        dto.setTelefone(obj.getTelefone());
        dto.setCelular(obj.getCelular());
        return dto;
    }

}
