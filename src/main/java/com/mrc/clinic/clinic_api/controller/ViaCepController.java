package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.entity.rec.ViaCepRec;
import com.mrc.clinic.clinic_api.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/ceps")
public class ViaCepController {
    @Autowired
    private ViaCepService service;

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepRec> findCep(@PathVariable String cep) {
        ViaCepRec viaCepRec = service.findCep(cep);
        return (viaCepRec != null) ? ResponseEntity.ok(viaCepRec) : ResponseEntity.notFound().build();
    }

}

