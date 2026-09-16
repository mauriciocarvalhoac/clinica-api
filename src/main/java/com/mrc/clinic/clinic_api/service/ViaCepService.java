package com.mrc.clinic.clinic_api.service;

import com.mrc.clinic.clinic_api.entity.rec.ViaCepRec;

public interface ViaCepService {
    ViaCepRec findCep(String cep);
}
