package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.FuncionarioDTO;
import com.mrc.clinic.clinic_api.service.FuncionarioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<FuncionarioDTO> save(@Valid @RequestBody FuncionarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
  
    @GetMapping("/{id}/usuario")
    public ResponseEntity<FuncionarioDTO> findUsuarioByFuncionarioId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findUsuarioByFuncionarioId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<FuncionarioDTO>> filterBy(@PathParam("nome") String nome, @PathParam("cpf") String cpf) {
        return ResponseEntity.ok(service.filterBy(nome, cpf));
    }

}

