package com.mrc.clinic.clinic_api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer("https://clinic.mauriciocarvalho.net/")
                .withClaim("username", user.getUsername())
                .withExpiresAt(LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("71988417444719884174447198841744471988417444"));
    }

}
