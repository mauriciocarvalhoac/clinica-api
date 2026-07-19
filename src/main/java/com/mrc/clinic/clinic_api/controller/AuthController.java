package com.mrc.clinic.clinic_api.controller;

import com.mrc.clinic.clinic_api.config.security.TokenService;
import com.mrc.clinic.clinic_api.config.security.dto.TokenRec;
import com.mrc.clinic.clinic_api.entity.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService service;

    @PostMapping
    public ResponseEntity<TokenRec> login(@RequestBody UsuarioDTO dto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = manager.authenticate(token);
        return ResponseEntity.ok(new TokenRec(service.gerarToken((User) authentication.getPrincipal())));
    }

}

