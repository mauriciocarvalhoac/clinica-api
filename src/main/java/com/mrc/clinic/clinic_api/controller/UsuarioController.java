package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.UsuarioDTO;
import com.mrc.clinic.clinic_api.entity.rec.UsuarioRec;
import com.mrc.clinic.clinic_api.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping("/{idFuncionario}")
    public ResponseEntity<UsuarioRec> save(@PathVariable Long idFuncionario, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(idFuncionario, dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll());
    }

    @PutMapping("/{idFuncionario}")
    public ResponseEntity<UsuarioRec> update(@PathVariable Long idFuncionario, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(idFuncionario, dto));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UsuarioDTO>> filter(@PathParam("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(service.filter(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}

