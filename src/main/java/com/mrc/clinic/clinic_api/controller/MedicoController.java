package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.MedicoDTO;
import com.mrc.clinic.clinic_api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {
    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity<MedicoDTO> save(@RequestBody MedicoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

