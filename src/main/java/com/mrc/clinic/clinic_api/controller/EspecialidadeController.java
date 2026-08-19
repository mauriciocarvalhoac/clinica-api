package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.dto.EspecialidadeDTO;
import com.mrc.clinic.clinic_api.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/filtro")
//    public ResponseEntity<List<PacienteDTO>> filterBy(@PathParam("nome") String nome, @PathParam("cpf") String cpf) {
//        return ResponseEntity.ok(service.filterBy(nome, cpf));
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(service.findById(id));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Long> delete(@PathVariable Long id) {
//        return ResponseEntity.ok(service.delete(id));
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody PacienteDTO dto) {
//        return ResponseEntity.ok(service.update(id, dto));
//    }

}

