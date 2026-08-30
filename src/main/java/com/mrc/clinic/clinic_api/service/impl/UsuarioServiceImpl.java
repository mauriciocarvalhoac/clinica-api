package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Funcionario;
import com.mrc.clinic.clinic_api.entity.Usuario;
import com.mrc.clinic.clinic_api.entity.dto.FuncionarioDTO;
import com.mrc.clinic.clinic_api.entity.dto.UsuarioDTO;
import com.mrc.clinic.clinic_api.entity.rec.UsuarioRec;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectExistingException;
import com.mrc.clinic.clinic_api.repository.FuncionarioRepository;
import com.mrc.clinic.clinic_api.repository.UsuarioRepository;
import com.mrc.clinic.clinic_api.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private FuncionarioRepository repoFuncionario;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioRec save(UsuarioDTO dto) {
        Optional<Usuario> opt = repository.findByUsername(dto.getUsername());
        if (opt.isEmpty()) {
            Funcionario funcionario = repoFuncionario.getReferenceById(dto.getFuncionario().getId());
            Usuario usuario = repository.save(to(dto, funcionario));
            funcionario.setUsuario(usuario);
            repoFuncionario.save(funcionario);
            return toRec(usuario);
        }
        throw new ObjectExistingException("Esse Usuário já existe.");
    }

    @Override
    public UsuarioRec update(Long id, UsuarioDTO dto) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            Usuario callback = repository.save(to(dto, usuario.get().getFuncionario()));
            return toRec(callback);
        }
        throw new ObjectExistingException("Esse Usuário já existe.");
    }

    @Override
    public List<UsuarioDTO> listAll() {
        return repository.list().stream().map(this::to).toList();
    }

    @Override
    public List<UsuarioDTO> filter(String username) {
        Usuario filter = new Usuario();
        filter.setUsername(username);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Usuario> example = Example.of(filter, exampleMatcher);
        return repository.findAll(example).stream().map(this::to).toList();
    }

    private UsuarioRec toRec(Usuario obj) {
        return new UsuarioRec(obj.getUsername());
    }

    private UsuarioDTO to(Usuario obj) {
        UsuarioDTO dto = new UsuarioDTO();
        BeanUtils.copyProperties(obj, dto);
        dto.setFuncionario(new FuncionarioDTO());
        BeanUtils.copyProperties((obj.getFuncionario() == null) ? new Funcionario() : obj.getFuncionario(), dto.getFuncionario());
        return dto;
    }

    private Usuario to(UsuarioDTO dto, Funcionario funcionario) {
        Usuario obj = new Usuario();
        obj.setId(dto.getId());
        obj.setUsername(dto.getUsername());
        obj.setPassword(passwordEncoder.encode(dto.getPassword()));
        obj.setEmailCorporativo(dto.getEmailCorporativo());
        obj.setSituacao(dto.getSituacao());
        obj.setRole(dto.getRole());
        obj.setFuncionario(funcionario);
        return obj;
    }
}
