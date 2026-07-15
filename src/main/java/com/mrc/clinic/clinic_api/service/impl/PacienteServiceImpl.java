package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Paciente;
import com.mrc.clinic.clinic_api.entity.dto.PacienteDTO;
import com.mrc.clinic.clinic_api.repository.PacienteRepository;
import com.mrc.clinic.clinic_api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository repository;

    @Override
    public PacienteDTO save(PacienteDTO dto) {

        Paciente saved = repository.save(Paciente.to(dto));
        return PacienteDTO.to(saved);
    }

    @Override
    public PacienteDTO findById(Long id) {
        return repository.findById(id)
                .map(this::to)
                .orElseThrow(() -> new RuntimeException("Não encontrado"));
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
