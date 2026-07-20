package com.mrc.clinic.clinic_api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(User user) {
        try {
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("https://clinic.mauriciocarvalho.net")
                    .withExpiresAt(LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00")))
                    .sign(Algorithm.HMAC256("71988417444719884174447198841744471988417444"));
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao gerar token: ", ex);
        }
    }

    public String validarToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256("71988417444719884174447198841744471988417444"))
                    .withIssuer("https://clinic.mauriciocarvalho.net")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
