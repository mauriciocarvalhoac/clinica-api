package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import com.mrc.clinic.clinic_api.service.EspecialidadeService;
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

//    @GetMapping("/filtro")
//    public ResponseEntity<List<PacienteDTO>> filterBy(@PathParam("nome") String nome, @PathParam("cpf") String cpf) {
//        return ResponseEntity.ok(service.filterBy(nome, cpf));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody PacienteDTO dto) {
//        return ResponseEntity.ok(service.update(id, dto));
//    }

}

