package com.mrc.clinic.clinic_api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/pacientes/**").permitAll();
            auth.requestMatchers("/medicos/**").permitAll();
            auth.anyRequest().authenticated();
        });
        return http.build();
    }
}
