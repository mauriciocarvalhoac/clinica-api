package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import com.mrc.clinic.clinic_api.service.EspecialidadeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/especialidades")
public class EspecialidadeController {
    @Autowired
    private EspecialidadeService service;

    @GetMapping
    public ResponseEntity<List<EspecialidadeDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping
    public ResponseEntity<EspecialidadeDTO> save(@RequestBody EspecialidadeDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<EspecialidadeDTO>> filterBy(@PathParam("descricao") String descricao, @PathParam("situacao") String situacao) {
        return ResponseEntity.ok(service.filterBy(descricao, situacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> update(@PathVariable Long id, @RequestBody EspecialidadeDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

}

