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
}
