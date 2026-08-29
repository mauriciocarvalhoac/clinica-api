package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.UsuarioDTO;
import com.mrc.clinic.clinic_api.entity.rec.UsuarioRec;
import com.mrc.clinic.clinic_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioRec> save(@Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRec> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, dto));
    }

}

