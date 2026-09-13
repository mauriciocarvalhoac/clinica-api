package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.DepartamentoDTO;
import com.mrc.clinic.clinic_api.service.DepartamentoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService service;

    @PostMapping
    public ResponseEntity<DepartamentoDTO> save(@Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/ativos")
    public ResponseEntity<List<DepartamentoDTO>> findAllAtivos() {
        return ResponseEntity.ok(service.findAllAtivos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<DepartamentoDTO>> filter(@PathParam("descricao") String descricao, @PathParam("situacao") String situacao) {
        return ResponseEntity.ok(service.filter(descricao, situacao));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable Long id, @Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

