package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Usuario;
import com.mrc.clinic.clinic_api.entity.dto.UsuarioDTO;
import com.mrc.clinic.clinic_api.entity.rec.UsuarioRec;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.repository.UsuarioRepository;
import com.mrc.clinic.clinic_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioRec save(UsuarioDTO dto) {
        Optional<Usuario> opt = repository.findByUsername(dto.getUsername());
        if (opt.isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            Usuario saved = repository.save(to(dto));
            return toRec(saved);
        }
        throw new ObjectExistingException("Esse Usuário já existe.");
    }

    private UsuarioRec toRec(Usuario obj) {
        return new UsuarioRec(obj.getUsername(), obj.getRole());
    }

    private Usuario to(UsuarioDTO dto) {
        Usuario obj = new Usuario();
        obj.setId(dto.getId());
        obj.setUsername(dto.getUsername());
        obj.setPassword(dto.getPassword());
        obj.setRole(dto.getRole());
        return obj;

    }
}
