package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Paciente;
import com.mrc.clinic.clinic_api.entity.dto.PacienteDTO;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.PacienteRepository;
import com.mrc.clinic.clinic_api.service.PacienteService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Override
    public PacienteDTO save(PacienteDTO dto) {
        Optional<Paciente> opt = repository.findByCpf(dto.getCpf());
        if (opt.isEmpty()) {
            Paciente saved = repository.save(to(dto));
            return to(saved);
        }
        throw new ObjectExistingException("Esse CPF já existe.");
    }

    @Override
    public List<PacienteDTO> listAll() {
        return repository.findAll().stream()
                .map(this::to)
                .sorted(Comparator.comparing(PacienteDTO::getNome))
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDTO findById(Long id) {
        return repository.findById(id)
                .map(this::to)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente Não encontrado."));
    }

    @Override
    public @Nullable Long delete(Long id) {
        Optional<Paciente> opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.deleteById(id);
            return id;
        }
        throw new ObjectNotFoundException("Id " + id + " não pode ser excluído.");
    }

    @Override
    public @Nullable PacienteDTO update(Long id, PacienteDTO dto) {
        Optional<Paciente> optPaciente = repository.findById(id);
        if (optPaciente.isPresent()) {
            dto.setId(id);
            Optional<Paciente> optCpf = repository.findByCpf(dto.getCpf());
            if (optCpf.isPresent() && dto.getCpf().equals(optCpf.get().getCpf()) && !id.equals(optCpf.get().getId())) {
                throw new ObjectExistingException("Este cpf já está cadastrado.");
            }
            Paciente pacienteSaved = repository.save(to(dto));
            return to(pacienteSaved);
        }
        throw new ObjectNotFoundException("Id " + id + " não pode ser atualizado.");
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
