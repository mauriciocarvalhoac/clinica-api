package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.AgendaDTO;
import com.mrc.clinic.clinic_api.service.AgendaService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/agendas")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @PostMapping
    public ResponseEntity<AgendaDTO> save(@Valid @RequestBody AgendaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<AgendaDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<AgendaDTO>> filterBy(@PathParam("descricao") String descricao) {
        return ResponseEntity.ok(service.filterBy(descricao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> findAgenda(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAgenda(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDTO> update(@PathVariable Long id, @Valid @RequestBody AgendaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}

