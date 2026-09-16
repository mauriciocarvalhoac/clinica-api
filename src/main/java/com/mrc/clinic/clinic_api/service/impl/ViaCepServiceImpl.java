package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.rec.ViaCepRec;
import com.mrc.clinic.clinic_api.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ViaCepServiceImpl implements ViaCepService {
    @Autowired
    private WebClient client;

    @Override
    public ViaCepRec findCep(String cep) {
        return client.get()
                .uri("{cep}/json/", cep)
                .retrieve()
                .bodyToMono(ViaCepRec.class)
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(e -> Mono.empty())
                .block();
    }
}