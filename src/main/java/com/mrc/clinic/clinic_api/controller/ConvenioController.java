package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.ConvenioDTO;
import com.mrc.clinic.clinic_api.service.ConvenioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/convenios")
public class ConvenioController {
    @Autowired
    private ConvenioService service;

    @PostMapping
    public ResponseEntity<ConvenioDTO> save(@Valid @RequestBody ConvenioDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConvenioDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvenioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvenioDTO> update(@PathVariable Long id, @RequestBody ConvenioDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ConvenioDTO>> filterBy(@PathParam("descricao") String descricao, @PathParam("situacao") String situacao) {
        return ResponseEntity.ok(service.filterBy(descricao, situacao));
    }

}

