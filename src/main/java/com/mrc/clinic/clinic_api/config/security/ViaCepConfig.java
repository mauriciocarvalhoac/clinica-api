package com.mrc.clinic.clinic_api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ViaCepConfig {

    @Bean
    public WebClient viaCepClient() {
        return WebClient.builder()
                .baseUrl("https://viacep.com.br/ws/")
                .build();
    }
}
